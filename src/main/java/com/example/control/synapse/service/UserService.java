package com.example.control.synapse.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.control.synapse.dto.request.PasswordChangeDto;
import com.example.control.synapse.dto.request.UserUpdateDto;
import com.example.control.synapse.dto.response.UserResponseDto;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * PUT /users/{id}
     * Update profile — users can only update their own, admins can update any
     */
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateDto updateDto) {
        log.info("Updating user with ID: {}", id);

        // ✅ Fixed: this call was accidentally removed — without it any user could update anyone
        checkUserAccess(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (updateDto.getFirstName() != null) {
            user.setFirstName(updateDto.getFirstName());
        }

        if (updateDto.getLastName() != null) {
            user.setLastName(updateDto.getLastName());
        }

        if (updateDto.getEmail() != null && !updateDto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updateDto.getEmail())) {
                throw new IllegalArgumentException("Email already in use: " + updateDto.getEmail());
            }
            user.setEmail(updateDto.getEmail());
        }

        if (updateDto.getPhoneNumber() != null) {
            user.setPhoneNumber(updateDto.getPhoneNumber());
        }

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", id);

        return convertToResponseDto(updatedUser);
    }

    /**
     * PUT /users/{id}/password
     * Change password — users can only change their own password
     * ✅ Fixed: now takes PasswordChangeDto and validates confirmPassword
     */
    @Transactional
    public void changePassword(Long id, PasswordChangeDto passwordChangeDto) {
        log.info("Changing password for user ID: {}", id);

        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(id)) {
            throw new AccessDeniedException("You can only change your own password");
        }

        // ✅ Fixed: confirmPassword was never validated in the original
        if (!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user ID: {}", id);
    }

    /**
     * DELETE /users/{id}
     * Delete own account — requires password confirmation
     */
    @Transactional
    public void deleteAccount(Long id, String password) {
        log.info("Deleting account for user ID: {}", id);

        User currentUser = getCurrentUser();

        if (!currentUser.getId().equals(id)) {
            throw new AccessDeniedException("You can only delete your own account");
        }

        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (!passwordEncoder.matches(password, userToDelete.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        userRepository.delete(userToDelete);
        log.info("Account deleted successfully for user ID: {}", id);
    }

    // ─── Admin-only helpers (kept — useful for admin panel) ──────────────────

    /**
     * GET /users — Admin only
     */
    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users");
        User currentUser = getCurrentUser();

        if (!currentUser.getRoles().contains(User.Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("Only admins can view all users");
        }

        return userRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * PATCH /users/{id}/status — Admin only
     */
    @Transactional
    public void toggleUserStatus(Long id, Boolean enabled) {
        log.info("Toggling user status for ID: {} to {}", id, enabled);
        User currentUser = getCurrentUser();

        if (!currentUser.getRoles().contains(User.Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("Only admins can change user status");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setIsEnabled(enabled);
        userRepository.save(user);

        log.info("User status toggled successfully for ID: {}", id);
    }

    // ─── Private helpers ─────────────────────────────────────────────────────

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new RuntimeException("No authenticated user found");
        }

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }

    private void checkUserAccess(Long userId) {
        User currentUser = getCurrentUser();

        if (currentUser.getRoles().contains(User.Role.ROLE_ADMIN)) {
            return; // Admins can access any user
        }

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException("You don't have permission to access this user's data");
        }
    }

    private UserResponseDto convertToResponseDto(User user) {
        List<String> roleNames = user.getRoles().stream()
                .map(role -> role.name())
                .collect(Collectors.toList());

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .roles(roleNames)
                .enabled(user.getIsEnabled())
                .locked(user.getIsLocked())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

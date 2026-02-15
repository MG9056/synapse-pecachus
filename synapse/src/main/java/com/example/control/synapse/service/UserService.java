package com.example.control.synapse.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * Get user by ID
     * Users can only view their own profile, admins can view any profile
     */
    public UserResponseDto getUserById(Long id) {
        log.info("Fetching user with ID: {}", id);
        
        // Check if current user has permission
        checkUserAccess(id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        return convertToResponseDto(user);
    }

    /**
     * Get current logged-in user's profile
     */
    public UserResponseDto getCurrentUserProfile() {
        log.info("Fetching current user profile");
        
        User currentUser = getCurrentUser();
        return convertToResponseDto(currentUser);
    }

    /**
     * Update user profile
     * Users can only update their own profile, admins can update any profile
     */
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateDto updateDto) {
        log.info("Updating user with ID: {}", id);
        
        // Check if current user has permission
        checkUserAccess(id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Update fields
        if (updateDto.getFirstName() != null) {
            user.setFirstName(updateDto.getFirstName());
        }
        
        if (updateDto.getLastName() != null) {
            user.setLastName(updateDto.getLastName());
        }
        
        if (updateDto.getEmail() != null && !updateDto.getEmail().equals(user.getEmail())) {
            // Check if email is already taken
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
     * Change user password
     * Requires old password verification
     */
    @Transactional
    public void changePassword(Long id, String oldPassword, String newPassword) {
        log.info("Changing password for user ID: {}", id);
        
        // Users can only change their own password
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(id)) {
            throw new AccessDeniedException("You can only change your own password");
        }
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        
        // Update to new password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        log.info("Password changed successfully for user ID: {}", id);
    }

    /**
     * Delete user account
     * Users can delete their own account with password verification
     * Admins can delete any account
     */
    @Transactional
    public void deleteAccount(Long id, String password) {
        log.info("Deleting account for user ID: {}", id);
        
        User currentUser = getCurrentUser();
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Check permissions
        boolean isAdmin = currentUser.getRoles().contains(User.Role.ROLE_ADMIN);
        boolean isOwnAccount = currentUser.getId().equals(id);
        
        if (!isAdmin && !isOwnAccount) {
            throw new AccessDeniedException("You can only delete your own account");
        }
        
        // If deleting own account, verify password
        if (isOwnAccount && !isAdmin) {
            if (!passwordEncoder.matches(password, userToDelete.getPassword())) {
                throw new IllegalArgumentException("Password is incorrect");
            }
        }
        
        userRepository.delete(userToDelete);
        log.info("Account deleted successfully for user ID: {}", id);
    }

    /**
     * Get all users (Admin only)
     */
    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users");
        
        // Check if current user is admin
        User currentUser = getCurrentUser();
        if (!currentUser.getRoles().contains(User.Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("Only admins can view all users");
        }
        
        return userRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Enable/Disable user account (Admin only)
     */
    @Transactional
    public void toggleUserStatus(Long id, Boolean enabled) {
        log.info("Toggling user status for ID: {} to {}", id, enabled);
        
        // Check if current user is admin
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

    /**
     * Helper method to get current authenticated user
     */
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }

    /**
     * Helper method to check if current user has access to view/modify another user
     */
    private void checkUserAccess(Long userId) {
        User currentUser = getCurrentUser();
        
        // Admins can access any user
        boolean isAdmin = currentUser.getRoles().contains(User.Role.ROLE_ADMIN);
        if (isAdmin) {
            return;
        }
        
        // Users can only access their own data
        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException("You don't have permission to access this user's data");
        }
    }

    /**
     * Convert User entity to UserResponseDto
     */
    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEnabled(user.getIsEnabled());
        dto.setLocked(user.getIsLocked());
        
        // Convert roles enum to strings
        List<String> roleNames = user.getRoles().stream()
                .map(role -> role.name())
                .collect(Collectors.toList());
        dto.setRoles(roleNames);
        
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        
        return dto;
    }
}

package com.example.control.synapse.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.PasswordChangeDto;
import com.example.control.synapse.dto.request.UserUpdateDto;
import com.example.control.synapse.dto.response.MessageResponse;
import com.example.control.synapse.dto.response.UserResponseDto;
import com.example.control.synapse.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get current user's profile
     * GET /api/users/me
     */
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> getCurrentUserProfile() {
        UserResponseDto user = userService.getCurrentUserProfile();
        return ResponseEntity.ok(user);
    }

    /**
     * Get user by ID
     * GET /api/users/{id}
     * Users can view their own profile, admins can view any profile
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users (Admin only)
     * GET /api/users
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Update user profile
     * PUT /api/users/{id}
     * Users can update their own profile, admins can update any profile
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDto updateDto) {
        
        UserResponseDto updatedUser = userService.updateUser(id, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Change password
     * PUT /api/users/{id}/password
     * Users can only change their own password
     */
    @PutMapping("/{id}/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        
        // Validate that new password and confirm password match
        if (!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("New password and confirm password do not match"));
        }
        
        userService.changePassword(id, passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
        return ResponseEntity.ok(new MessageResponse("Password changed successfully"));
    }

    /**
     * Delete user account
     * DELETE /api/users/{id}
     * Users can delete their own account with password verification
     * Admins can delete any account
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> deleteAccount(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        
        String password = request.get("password");
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Password is required"));
        }
        
        userService.deleteAccount(id, password);
        return ResponseEntity.ok(new MessageResponse("Account deleted successfully"));
    }

    /**
     * Enable/Disable user account (Admin only)
     * PATCH /api/users/{id}/status
     */
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> toggleUserStatus(
            @PathVariable Long id,
            @RequestParam Boolean enabled) {
        
        userService.toggleUserStatus(id, enabled);
        String message = enabled ? "User account enabled successfully" : "User account disabled successfully";
        return ResponseEntity.ok(new MessageResponse(message));
    }
}

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
import com.example.control.synapse.service.interfaces.IUserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    /**
     * PUT /users/{id}
     * Body: { "firstName", "lastName", "phoneNumber", "email" }
     * Users can only update their own profile; admins can update any
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
     * PUT /users/{id}/password
     * Body: { "currentPassword", "newPassword", "confirmPassword" }
     * Users can only change their own password
     */
    @PutMapping("/{id}/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.changePassword(id, passwordChangeDto);
        return ResponseEntity.ok(new MessageResponse("Password changed successfully!"));
    }

    /**
     * DELETE /users/{id}
     * Body: { "password": "yourpassword" }
     * Users can only delete their own account
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> deleteAccount(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String password = body.get("password");
        if (password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Password is required to delete account"));
        }
        userService.deleteAccount(id, password);
        return ResponseEntity.ok(new MessageResponse("Account deleted successfully!"));
    }
}

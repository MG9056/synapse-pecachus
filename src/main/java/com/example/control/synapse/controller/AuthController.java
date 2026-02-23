package com.example.control.synapse.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.LoginRequest;
import com.example.control.synapse.dto.request.RegisterRequest;
import com.example.control.synapse.dto.response.JwtResponse;
import com.example.control.synapse.dto.response.MessageResponse;
import com.example.control.synapse.dto.response.UserResponseDto;

import com.example.control.synapse.service.interfaces.IAuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    /**
     * POST /auth/login
     * Body: { "username": "", "password": "" }
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * POST /auth/register
     * Body: {
     * "username","gender","email","password","firstName","lastName","phoneNumber","roles"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /**
     * GET /auth/me
     * Returns current user's profile — no password exposed
     * Requires valid JWT
     */
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        UserResponseDto userProfile = authService.getCurrentUserProfile();
        return ResponseEntity.ok(userProfile);
    }
}
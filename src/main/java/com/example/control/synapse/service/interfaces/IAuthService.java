package com.example.control.synapse.service.interfaces;

import com.example.control.synapse.dto.request.LoginRequest;
import com.example.control.synapse.dto.request.RegisterRequest;
import com.example.control.synapse.dto.response.JwtResponse;
import com.example.control.synapse.dto.response.UserResponseDto;
import com.example.control.synapse.models.User;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    void registerUser(RegisterRequest registerRequest);

    UserResponseDto getCurrentUserProfile();

    User getCurrentUser();
}

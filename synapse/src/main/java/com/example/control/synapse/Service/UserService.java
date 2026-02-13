package com.example.control.synapse.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.UserResponseDto;
import com.example.control.synapse.mapper.UserMapper;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponseDto signUp(String name,String email,String password,String role,String gender) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(name,email,hashedPassword,role,gender);
        userRepository.save(user);
        
    }
}

package com.example.control.synapse.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.UserResponseDto;
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
    public Map<String,String> signUp(String name,String email,String password,String role,String gender) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(name,email,hashedPassword,role,gender);
        userRepository.save(user);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Account created successfully");
        return response;

        
    }
    public Map<String,String> deleteAccount(Long id,String password) {
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("No such user with exists with id"+id));
        if (passwordEncoder.matches(password, user.getPassword())) {
            userRepository.delete(user);
            response.put("message","Account successfully deleted with email"+user.getEmail());
            
        } else {
            response.put("message","Password did not match");
        }
        return response;
    }
    public Map<String,String> updateAccount(Long id,String name,String email,String password,String gender) {
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("No such user with exists with id"+id));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        response.put("message","Successfully update user details with id"+id);
        return response;

    }
    public UserResponseDto getAccount(Long id) {
        UserResponseDto dto = new UserResponseDto();
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("No such user with exists with id"+id));
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        return dto;
    }

}

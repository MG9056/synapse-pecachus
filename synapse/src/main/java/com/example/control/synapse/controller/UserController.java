package com.example.control.synapse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.example.control.synapse.dto.request.UserSignupDto;
import com.example.control.synapse.dto.request.UserUpdateDto;
import com.example.control.synapse.dto.response.UserResponseDto;
import com.example.control.synapse.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

   
    private UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }
    @PostMapping
    public Map<String,String> signup(@RequestBody UserSignupDto request) {
        return userService.signUp(request.getName(),
        request.getEmail(),
        request.getPassword(),
        request.getRole(),
        request.getGender());
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteAccount(@RequestBody String password,@PathVariable Long id) {
        return userService.deleteAccount(id,password);
    }
    @PutMapping("{id}")
    public Map<String,String> updateAccount(@RequestBody UserUpdateDto request,@PathVariable Long id) {
        return userService.updateAccount(id,request.getName(),request.getEmail(),request.getPassword(),request.getGender());
    }
    @GetMapping("{id}")
    public UserResponseDto getAccount(@PathVariable Long id) {
        return userService.getAccount(id);
    }
}

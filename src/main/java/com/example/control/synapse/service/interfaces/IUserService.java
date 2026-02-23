package com.example.control.synapse.service.interfaces;

import com.example.control.synapse.dto.request.PasswordChangeDto;
import com.example.control.synapse.dto.request.UserUpdateDto;
import com.example.control.synapse.dto.response.UserResponseDto;

public interface IUserService {
    UserResponseDto updateUser(Long id, UserUpdateDto updateDto);

    void changePassword(Long id, PasswordChangeDto passwordChangeDto);

    void deleteAccount(Long id, String password);
}

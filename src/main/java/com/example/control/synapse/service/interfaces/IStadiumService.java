package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;

public interface IStadiumService {
    Map<String, String> uploadStadium(String city, String state, String country, Integer capacity,
            String name, String adminEmail);

    StadiumResponseDto getStadiumById(Long stadiumId);

    List<StadiumResponseDto> getAllStadiums();

    Map<String, String> updateStadium(Long stadiumId, String city, String state, String country,
            Integer capacity, String name, String adminEmail);

    Map<String, String> deleteStadium(Long stadiumId, DeleteCredentialsDto deleteCredentialsDto);
}

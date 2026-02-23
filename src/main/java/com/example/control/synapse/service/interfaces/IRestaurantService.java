package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;

public interface IRestaurantService {
    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto getRestaurantById(Long id);

    List<RestaurantResponseDto> getRestaurantByStadiumId(Long stadiumId);

    Map<String, String> uploadRestaurant(String name, Double rating, Long stadiumId);

    Map<String, String> updateRestaurant(Long id, String name, Double rating, Long stadiumId);

    Map<String, String> deleteRestaurant(Long id, DeleteCredentialsDto deleteCredentialsDto);
}

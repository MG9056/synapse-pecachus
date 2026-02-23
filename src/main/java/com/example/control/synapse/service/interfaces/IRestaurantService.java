package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;

public interface IRestaurantService {
    Map<String, String> uploadRestaurant(String name, double rating, Long stadiumId);

    RestaurantResponseDto getRestaurantById(Long id);

    List<RestaurantResponseDto> getRestaurantByStadiumId(Long stadiumId);

    List<RestaurantResponseDto> getAllRestaurants();

    Map<String, String> updateRestaurant(Long restaurantId, String name, Double rating, Long stadiumId);

    Map<String, String> deleteRestaurant(Long restaurantId, DeleteCredentialsDto deleteCredentialsDto);
}

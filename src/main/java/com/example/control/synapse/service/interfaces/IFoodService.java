package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.FoodResponseDto;

public interface IFoodService {
    List<FoodResponseDto> getAllFoods();

    FoodResponseDto getFoodById(Long id);

    List<FoodResponseDto> getFoodByRestaurantId(Long restaurantId);

    List<FoodResponseDto> getFoodByDiet(String diet);

    List<FoodResponseDto> getFoodByType(String type);

    Map<String, String> uploadFood(String name, String description, Double price, Double rating,
            Long restaurantId, String type, String diet, Integer stock);

    Map<String, String> updateFood(Long id, String name, String description, Double price, Double rating,
            Long restaurantId, String type, String diet, Integer stock);

    Map<String, String> deleteFood(Long id, DeleteCredentialsDto deleteCredentialsDto);
}

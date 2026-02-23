package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.FoodResponseDto;

public interface IFoodService {
        Map<String, String> uploadFood(String name, Long restaurantId, float price, float rating, String type,
                        String diet, Integer stock);

        Map<String, String> updateFood(Long foodId, String name, Long restaurantId, Float price, Float rating,
                        String type, String diet, Integer stock);

        List<FoodResponseDto> getFoodByRestaurantId(Long restaurantId);

        List<FoodResponseDto> getAllFoods();

        FoodResponseDto getFoodById(Long foodId);

        List<FoodResponseDto> getFoodByDiet(String diet);

        List<FoodResponseDto> getFoodByType(String type);

        Map<String, String> deleteFood(Long foodId, DeleteCredentialsDto deleteCredentialsDto);
}

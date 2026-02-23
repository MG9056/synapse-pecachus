package com.example.control.synapse.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.FoodOrderResponseDto;

public interface IFoodOrderService {
    Map<String, String> bookFoodOrder(List<Long> foodIdlist, Long userId, float price, Long seatId,
            Long restaurantId, Long eventId, LocalDateTime orderTime);

    List<FoodOrderResponseDto> getFoodOrderByUserId(Long userId);

    List<FoodOrderResponseDto> getAllFoodOrders();

    List<FoodOrderResponseDto> getFoodOrderByRestaurantId(Long restaurantId);

    FoodOrderResponseDto getFoodOrderById(Long id);

    List<FoodOrderResponseDto> getFoodOrderByEventId(Long eventId);
}

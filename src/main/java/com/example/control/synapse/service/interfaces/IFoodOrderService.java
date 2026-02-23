package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.FoodOrderResponseDto;

public interface IFoodOrderService {
    List<FoodOrderResponseDto> getAllFoodOrders();

    FoodOrderResponseDto getFoodOrderById(Long id);

    Map<String, String> bookFoodOrder(List<Long> foodIdList, Long userId, Double price, Long seatId,
            Long restaurantId, Long eventId, String orderTime);

    List<FoodOrderResponseDto> getFoodOrderByUserId(Long userId);

    List<FoodOrderResponseDto> getFoodOrderByRestaurantId(Long restaurantId);

    List<FoodOrderResponseDto> getFoodOrderByEventId(Long eventId);
}

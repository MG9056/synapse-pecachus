package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.FoodOrderRequest;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
import com.example.control.synapse.service.interfaces.IFoodOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/foodOrder")
@RequiredArgsConstructor
public class FoodOrderController {

    private final IFoodOrderService foodOrderService;

    @GetMapping("/allFoodOrders")
    public List<FoodOrderResponseDto> getAllFoodOrders() {
        return foodOrderService.getAllFoodOrders();
    }

    @GetMapping("/{id}")
    public FoodOrderResponseDto getOrderById(@PathVariable Long id) {
        return foodOrderService.getFoodOrderById(id);
    }

    @PostMapping("/placeFoodOrder")
    public Map<String, String> bookFoodOrder(@RequestBody FoodOrderRequest request) {
        return foodOrderService.bookFoodOrder(
                request.getFoodIdlist(),
                request.getUserId(),
                request.getPrice(),
                request.getSeatId(),
                request.getRestaurantId(),
                request.getEventId(),
                request.getOrderTime());
    }

    @GetMapping("/user/{userId}")
    public List<FoodOrderResponseDto> getFoodOrderByUserId(@PathVariable Long userId) {
        return foodOrderService.getFoodOrderByUserId(userId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodOrderResponseDto> getFoodOrderByRestaurantId(@PathVariable Long restaurantId) {
        return foodOrderService.getFoodOrderByRestaurantId(restaurantId);
    }

    @GetMapping("/event/{eventId}")
    public List<FoodOrderResponseDto> getFoodOrderByEventId(@PathVariable Long eventId) {
        return foodOrderService.getFoodOrderByEventId(eventId);
    }

}

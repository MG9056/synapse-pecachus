package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FoodOrderRequest;
import com.example.control.synapse.dto.request.MerchandiseOrderRequest;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
import com.example.control.synapse.dto.response.FoodResponseDto;

import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.FoodOrderRepository;
import com.example.control.synapse.service.FoodOrderService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/FoodOrder")
public class FoodOrderController {

    private final FoodOrderService foodOrderService;
    private final FoodOrderRepository orderRepository;

    public FoodOrderController(FoodOrderService foodOrderSerive, FoodOrderRepository orderRepository)
    {
        this.foodOrderService=foodOrderSerive;
        this.orderRepository=orderRepository;
    }

    @PostMapping("/placeFoodOrder")
    public  Map<String,String> bookFoodOrder(@RequestBody FoodOrderRequest request)
    {

        return foodOrderService.bookFoodOrder(
            request.getFoodIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId(),
            request.getRestaurantId()
        );
    }

   

    @GetMapping("/user/{userId}")
    public List<FoodOrderResponseDto> getOrderByUserId(@PathVariable Long id)
    { return foodOrderService.getOrderByUserId(id);
        
    }

    @GetMapping("/restaurant/{restuarantId}")
    public List<FoodOrderResponseDto> getOrderByRestaurantId(@PathVariable Long id)
    {
        return foodOrderService.getOrderByRestaurantId(id);
    }

   
    







    
}

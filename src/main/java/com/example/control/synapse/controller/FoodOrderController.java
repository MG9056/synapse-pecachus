package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.FoodOrderRequest;


import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.repository.FoodOrderRepository;
import com.example.control.synapse.service.FoodOrderService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/foodOrder")
public class FoodOrderController {

    private final FoodOrderRepository foodOrderRepository;

    private final FoodOrderService foodOrderService;
    

    public FoodOrderController(FoodOrderService foodOrderSerive, FoodOrderRepository foodOrderRepository)
    {
        this.foodOrderService=foodOrderSerive;
        this.foodOrderRepository = foodOrderRepository;
       
    }

    @GetMapping("/allFoodOrders")
     public List<FoodOrder> getAllFoodOrders()
    { return foodOrderRepository.findAll();
        
    }

    @GetMapping("/{id}")
    public FoodOrder getOrderById(@PathVariable Long id)
    {
       return foodOrderRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food order not found with id " + id));
    }


    @PostMapping("/placeFoodOrder")
    public  Map<String,String> bookFoodOrder(@RequestBody FoodOrderRequest request)
    {

        return foodOrderService.bookFoodOrder(
            request.getFoodIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId(),
            request.getRestaurantId(),
            request.getEventId(),
            request.getOrderTime()
        );
    }

   

    @GetMapping("/user/{userId}")
    public List<FoodOrder> getFoodOrderByUserId(@PathVariable Long userId)
    { return foodOrderRepository.findByUser_Id(userId);
        
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodOrder> getFoodOrderByRestaurantId(@PathVariable Long restaurantId)
    {
        return foodOrderRepository.findByRestaurant_Id(restaurantId);
    }

      @GetMapping("/event/{eventId}")
    public List<FoodOrder> getFoodOrderByEventId(@PathVariable Long eventId)
    {
        return foodOrderRepository.findByEvent_Id(eventId);
    }

   
    







    
}

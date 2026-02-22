package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FoodOrderRequest;


import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.repository.FoodOrderRepository;
import com.example.control.synapse.service.FoodOrderService;

import java.util.List;
import java.util.Map;

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
        return foodOrderRepository.findById(id).orElseThrow();
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
    public List<FoodOrder> getFoodOrderByUserId(@PathVariable Long id)
    { return foodOrderRepository.findByUser_Id(id);
        
    }

    @GetMapping("/restaurant/{restuarantId}")
    public List<FoodOrder> getFoodOrderByRestaurantId(@PathVariable Long restaurantid)
    {
        return foodOrderRepository.findByRestaurant_Id(restaurantid);
    }

      @GetMapping("/event/{eventId}")
    public List<FoodOrder> getFoodOrderByEventId(@PathVariable Long eventid)
    {
        return foodOrderRepository.findByEvent_Id(eventid);
    }

   
    







    
}

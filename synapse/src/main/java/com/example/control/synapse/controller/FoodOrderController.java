package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FoodOrderRequest;

import com.example.control.synapse.dto.response.FoodOrderResponseDto;




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

    private final FoodOrderService foodOrderService;
    

    public FoodOrderController(FoodOrderService foodOrderSerive)
    {
        this.foodOrderService=foodOrderSerive;
       
    }

    @GetMapping("/allFoodOrders")
     public List<FoodOrderResponseDto> getAllFoodOrders()
    { return foodOrderService.getAllFoodOrders();
        
    }

    @GetMapping("/{id}")
    public FoodOrderResponseDto getOrderById(@PathVariable Long id)
    {
        return foodOrderService.getFoodOrderById(id);
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
    public List<FoodOrderResponseDto> getFoodOrderByUserId(@PathVariable Long id)
    { return foodOrderService.getFoodOrderByUserId(id);
        
    }

    @GetMapping("/restaurant/{restuarantId}")
    public List<FoodOrderResponseDto> getFoodOrderByRestaurantId(@PathVariable Long restaurantid)
    {
        return foodOrderService.getFoodOrderByRestaurantId(restaurantid);
    }

   
    







    
}

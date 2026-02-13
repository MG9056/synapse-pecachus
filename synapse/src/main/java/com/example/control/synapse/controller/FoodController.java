package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FoodRequest;
import com.example.control.synapse.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService)
    {
        this.foodService= foodService;
        
    }

    @PostMapping("/upload")
    public String uploadFood(@RequestBody FoodRequest foodRequest)
    {return foodService.uploadFood(
        foodRequest.getName(),
        foodRequest.getRestaurantId(),
        foodRequest.getPrice(),
        foodRequest.getRating()
        


    );

    }



    
}

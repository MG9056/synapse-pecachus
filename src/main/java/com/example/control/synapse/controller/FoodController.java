package com.example.control.synapse.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.FoodRequest;
import com.example.control.synapse.dto.request.FoodUpdateDto;
import com.example.control.synapse.dto.response.FoodResponseDto;



import com.example.control.synapse.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;
   

    public FoodController(FoodService foodService)
    {
        this.foodService= foodService;
        
        
    }
     
    @GetMapping("/{id}")
    public FoodResponseDto getFoodById(@PathVariable Long id)

    {
        return foodService.getFoodById(id);
    }    

    // @GetMapping("/restaurant/{restaurantId}")
    // public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long restaurantId)
    // {
    //     return foodService.getFoodByRestaurantId(restaurantId);

    // }


    @PostMapping("/upload")
    public Map<String,String> uploadFood(@RequestBody FoodRequest foodRequest)
    {return foodService.uploadFood(
        foodRequest.getName(),
        foodRequest.getRestaurantId(),
        foodRequest.getPrice(),
        foodRequest.getRating()
        


    );

    }

    @PatchMapping("/{id}")
    public Map<String,String> updateFood(@PathVariable Long id, @RequestBody FoodUpdateDto foodUpdateDto)
    {


        return foodService.updateFood(
            id,
            foodUpdateDto.getName(),
            foodUpdateDto.getRestaurantId(),
            foodUpdateDto.getPrice(),
            foodUpdateDto.getRating()





        );
    }

     @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteFood(@PathVariable Long id, @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return foodService.deleteFood(id,deleteCredentialsDto);
    }



    
}

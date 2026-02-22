package com.example.control.synapse.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.FoodRequest;
import com.example.control.synapse.dto.request.FoodUpdateDto;

import com.example.control.synapse.models.Food;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodRepository foodRepository;

    private final FoodService foodService;
   

    public FoodController(FoodService foodService, FoodRepository foodRepository)
    {
        this.foodService= foodService;
        this.foodRepository = foodRepository;
        
        
    }

    @GetMapping("/allFoods")
    public List<Food> getAllFoods()
    {return foodRepository.findAll();

    }
     
    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id)

    {
        return foodRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id " + id));
    }    

    // @GetMapping("/restaurant/{restaurantId}")
    // public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long restaurantId)
    // {
    //     return foodService.getFoodByRestaurantId(restaurantId);

    // }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Food> getFoodByRestaurantId(@PathVariable Long restaurantId)
    {
        return foodRepository.findByRestaurant_Id(restaurantId);
    }

    @GetMapping("/diet/{diet}")
    public List<Food> getFoodByDiet(@PathVariable String diet)
    {
        return foodRepository.findByDiet(diet);
    }

    @GetMapping("/type/{type}")
    public List<Food> getFoodByType(@PathVariable String type)
    {
        return foodRepository.findByType(type);
    }



    @PostMapping("/upload")
    public Map<String,String> uploadFood(@RequestBody FoodRequest foodRequest)
    {return foodService.uploadFood(
        foodRequest.getName(),
        foodRequest.getRestaurantId(),
        foodRequest.getPrice(),
        foodRequest.getRating(),
        foodRequest.getType(),
        foodRequest.getDiet(),
        foodRequest.getStock()
        


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
            foodUpdateDto.getRating(),
            foodUpdateDto.getType(),
            foodUpdateDto.getDiet(),
            foodUpdateDto.getStock()
            





        );
    }

     @DeleteMapping("/{id}")
    public Map<String,String> deleteFood(@PathVariable Long id, @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return foodService.deleteFood(id,deleteCredentialsDto);
    }



    
}

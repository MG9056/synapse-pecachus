package com.example.control.synapse.controller;

import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.RestaurantRequest;
import com.example.control.synapse.dto.request.RestaurantUpdateDto;

import com.example.control.synapse.models.Restaurant;

import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.service.FoodService;
import com.example.control.synapse.service.RestaurantService;



@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    public final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;
    private final FoodService foodService;

    public RestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService, FoodService foodService)
    {this.restaurantRepository= restaurantRepository;
       
        this.restaurantService= restaurantService;
        this.foodService= foodService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants()
        {
            return restaurantRepository.findAll();
        }

    @GetMapping("/{id}")
    public RestaurantResponseDto getRestaurantById(@PathVariable Long id)

    {
        return restaurantService.getRestaurantById(id);
    }    

    @GetMapping("{id}/menu")
    public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long restaurantId)
    { return foodService.getFoodByRestaurantId(restaurantId);
        
    }

    @GetMapping("stadiun/{stadiumId}")
    public List<RestaurantResponseDto> getRestaurantByRestaurantId(@PathVariable Long stadiumId)
    {
        return restaurantService.getRestaurantByStadiumId(stadiumId);

    }

    @PostMapping("/upload")
    public Map<String,String> uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    { return restaurantService.uploadRestaurant(
        restaurantRequest.getName(),
        restaurantRequest.getRating(),
        restaurantRequest.getStadiumId()

    );

    }

    @PatchMapping("/{id}/update")
    public Map<String,String> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateDto restaurantUpdateDto)
    {  return restaurantService.updateRestaurant(
        restaurantUpdateDto.getRestaurantId(),
        restaurantUpdateDto.getName(),
        restaurantUpdateDto.getRating(),
        restaurantUpdateDto.getStadiumId()




    );


    }

     @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteStadium(@RequestBody String password,@PathVariable Long id, @RequestBody Long restaurantId) {
        return restaurantService.deleteRestaurant(id,password,restaurantId);
    }



        
    


    
    
}

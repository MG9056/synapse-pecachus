package com.example.control.synapse.controller;

import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.RestaurantRequest;
import com.example.control.synapse.dto.request.RestaurantUpdateDto;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.service.FoodService;
import com.example.control.synapse.service.RestaurantService;



@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    public final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final RestaurantService restaurantService;
    private final FoodService foodService;

    public RestaurantController(RestaurantRepository restaurantRepository, FoodRepository foodRepository, RestaurantService restaurantService, FoodService foodService)
    {this.restaurantRepository= restaurantRepository;
        this.foodRepository = foodRepository;
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
    public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long id)
    { return foodService.getFoodByRestaurantId(id);
        
    }

    @PostMapping("/upload")
<<<<<<< HEAD
    public Map<String,String> uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest)
=======
    public  Map<String,String> uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest)
>>>>>>> 571528aed8dfd8d61658a96b04c87bf62084202e
    { return restaurantService.uploadRestaurant(
        restaurantRequest.getName(),
        restaurantRequest.getRating(),
        restaurantRequest.getStadiumId()

    );

    }

    @PatchMapping("/{id}/update")
<<<<<<< HEAD
    public Map<String,String> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateDto restaurantUpdateDto)
=======
    public  Map<String,String> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateDto restaurantUpdateDto)
>>>>>>> 571528aed8dfd8d61658a96b04c87bf62084202e
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

package com.example.control.synapse.controller;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.RestaurantRequest;
import com.example.control.synapse.dto.request.RestaurantUpdateDto;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;

import com.example.control.synapse.service.RestaurantService;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final FoodRepository foodRepository;
    public final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;
   

    public RestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService, FoodRepository foodRepository)
    {this.restaurantRepository= restaurantRepository;
       
        this.restaurantService= restaurantService;
        
        this.foodRepository = foodRepository;
    }

    @GetMapping("allRestaurants")
    public List<Restaurant> getAllRestaurants()
        {
            return restaurantRepository.findAll();
        }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id)

    {
        return restaurantRepository.findById(id).orElseThrow();
    }    

    @GetMapping("{id}/menu")
    public List<Food> getFoodByRestaurantId(@PathVariable Long restaurantId)
    { return foodRepository.findByRestaurant_Id(restaurantId);
        
    }

    @GetMapping("stadium/{stadiumId}")
    public List<Restaurant> getRestaurantByRestaurantId(@PathVariable Long stadiumId)
    {
        return restaurantRepository.findByStadium_Id(stadiumId);

    }

    @PostMapping("/upload")
    public Map<String,String> uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    { return restaurantService.uploadRestaurant(
        restaurantRequest.getName(),
        restaurantRequest.getRating(),
        restaurantRequest.getStadiumId()

    );

    }

    @PatchMapping("/{id}")
    public Map<String,String> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateDto restaurantUpdateDto)
    {  return restaurantService.updateRestaurant(
        id,
        restaurantUpdateDto.getName(),
        restaurantUpdateDto.getRating(),
        restaurantUpdateDto.getStadiumId()




    );


    }

     @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteRestaurant(@PathVariable Long id, @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return restaurantService.deleteRestaurant(id,deleteCredentialsDto);
    }



        
    


    
    
}

package com.example.control.synapse.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.control.synapse.dto.request.RestaurantRequest;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.service.RestaurantService;



@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    public final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantRepository restaurantRepository, FoodRepository foodRepository, RestaurantService restaurantService)
    {this.restaurantRepository= restaurantRepository;
        this.foodRepository = foodRepository;
        this.restaurantService= restaurantService;
    }

    @GetMapping
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
    public List<Food> getFoodByRestaurantId(@PathVariable Long id)
    { return foodRepository.findByRestaurantId(id);
        
    }

    @PostMapping("/upload")
    public String uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    { return restaurantService.uploadRestaurant(
        restaurantRequest.getName(),
        restaurantRequest.getRating(),
        restaurantRequest.getStadiumId()

    );

    }

        
    


    
    
}

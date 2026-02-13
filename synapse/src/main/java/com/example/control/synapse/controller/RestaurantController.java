package com.example.control.synapse.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.control.synapse.service.RestaurantService;



@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    public final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public RestaurantController(RestaurantRepository restaurantRepository, FoodRepository foodRepository)
    {this.restaurantRepository= restaurantRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants()
        {
            return restaurantRepository.findAll();
        }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id)

    {
        return restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }    

    @GetMapping("{id}/menu")
    public List<Food> getFoodByRestaurantId(@PathVariable Long id)
    { return foodRepository.findByRestaurantId(id);
        
    }

        
    


    
    
}

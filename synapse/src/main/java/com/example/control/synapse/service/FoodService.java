package com.example.control.synapse.service;



import org.springframework.stereotype.Service;

import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;

@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public FoodService(RestaurantRepository restaurantRepository, FoodRepository foodRepository)
    {
        this.restaurantRepository=restaurantRepository;
        this.foodRepository=foodRepository;
    }
    
   public String uploadFood(String name, Long restaurantId, float price, float rating )
   {Food food= new Food();

    food.setName(name);
    
      Restaurant restaurant= restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));


    food.setRestaurantId(restaurant);

    food.setPrice(price);
    food.setRating(rating);
    foodRepository.save(food);
    


    return "Food Item Uploaded!";
   }

}

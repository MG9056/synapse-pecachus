package com.example.control.synapse.service;



import org.springframework.stereotype.Service;

import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;

import com.example.control.synapse.repository.RestaurantRepository;

@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;

    public FoodService(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository=restaurantRepository;
    }
    
   public String uploadFood(String name, Long restaurantId, float price, float rating )
   {Food food= new Food();

    food.setName(name);
    
      Restaurant restaurant= restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));


    food.setRestaurantId(restaurant);

    food.setPrice(price);
    food.setRating(rating);
    


    return "Food Item Uploaded!";
   }

}

package com.example.control.synapse.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.FoodResponseDto;
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


   public String updateFood(Long foodId, String name, Long restaurantId, float price, float rating)
   {Food food= foodRepository.findById(foodId).orElseThrow();

    food.setName(name);
    
    Restaurant restaurant= restaurantRepository.findById(restaurantId).orElseThrow();
    food.setRestaurantId(restaurant);
    food.setPrice(price);
    food.setRating(rating);






    return "Food Item updated!";
   }


   public List<FoodResponseDto>  getFoodByRestaurantId(Long restaurantId)
   {List<Food> foods= foodRepository.findByRestaurantId(restaurantId);
    List<FoodResponseDto> dtoList= new ArrayList<>();

    for(Food food:foods)
    {

        FoodResponseDto foodResponseDto= new FoodResponseDto();
        foodResponseDto.setName(food.getName());
        foodResponseDto.setRestaurantId(food.getRestaurantId());
        foodResponseDto.setPrice(food.getPrice());
        foodResponseDto.setRating(food.getRating());

        dtoList.add(foodResponseDto);

    }

    return dtoList;

   }

   public FoodResponseDto getFoodById(Long foodId)
   {Food food= foodRepository.findById(foodId).orElseThrow();
    
    FoodResponseDto foodResponseDto= new FoodResponseDto();

    foodResponseDto.setName(food.getName());
    foodResponseDto.setRestaurantId((food.getRestaurantId()));
    foodResponseDto.setPrice(food.getPrice());
    foodResponseDto.setRating(food.getRating());

    return foodResponseDto;





   }

}

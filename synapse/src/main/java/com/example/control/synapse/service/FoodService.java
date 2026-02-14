package com.example.control.synapse.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.UserRepository;

@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public FoodService(RestaurantRepository restaurantRepository, FoodRepository foodRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.restaurantRepository=restaurantRepository;
        this.foodRepository=foodRepository;
        this.userRepository= userRepository;
        this.passwordEncoder= passwordEncoder;
    }
    
   public Map<String,String> uploadFood(String name, Long restaurantId, float price, float rating )
   {Food food= new Food();

    food.setName(name);
    
      Restaurant restaurant= restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));


    food.setRestaurantId(restaurant);

    food.setPrice(price);
    food.setRating(rating);
    foodRepository.save(food);
    


     Map<String,String> response = new HashMap<>();
        response.put("message", "Food uploaded successfully!");
        return response;
   }


   public Map<String,String> updateFood(Long foodId, String name, Long restaurantId, float price, float rating)
   {Food food= foodRepository.findById(foodId).orElseThrow();

    food.setName(name);
    
    Restaurant restaurant= restaurantRepository.findById(restaurantId).orElseThrow();
    food.setRestaurantId(restaurant);
    food.setPrice(price);
    food.setRating(rating);






     Map<String,String> response = new HashMap<>();
        response.put("message", "Food updated successfully!");
        return response;
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

   
    public Map<String,String> deleteFood(Long userId,String password, Long foodId) {
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("No such user with exists with id"+userId));
        Food food= foodRepository.findById(foodId).orElseThrow(()-> new RuntimeException("No such stadium with exists with id"+foodId));
        if (passwordEncoder.matches(password, user.getPassword())) {
            foodRepository.delete(food);
            response.put("message","Food successfully deleted with Food Id"+food.getId());
            
        } else {
            response.put("message","Password did not match");
        }
        return response;
    }

}

package com.example.control.synapse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.FoodRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.control.synapse.service.interfaces.IFoodService;

@Service
public class FoodService implements IFoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public FoodService(RestaurantRepository restaurantRepository, FoodRepository foodRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private FoodResponseDto convertToDto(Food food) {
        FoodResponseDto dto = new FoodResponseDto();
        dto.setId(food.getId());
        dto.setName(food.getName());
        dto.setRestaurantId(food.getRestaurant() != null ? food.getRestaurant().getId() : null);
        dto.setPrice(food.getPrice());
        dto.setRating(food.getRating());
        dto.setType(food.getType());
        dto.setDiet(food.getDiet());
        dto.setStock(food.getStock());
        return dto;
    }

    @Transactional
    public Map<String, String> uploadFood(String name, Long restaurantId, float price, float rating, String type,
            String diet, Integer stock) {
        Food food = new Food();
        food.setName(name);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restaurant not found with id " + restaurantId));

        food.setRestaurant(restaurant);
        food.setPrice(price);
        food.setRating(rating);
        food.setType(type);
        food.setDiet(diet);
        food.setStock(stock);
        foodRepository.save(food);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Food uploaded successfully!");
        return response;
    }

    @Transactional
    public Map<String, String> updateFood(Long foodId, String name, Long restaurantId, Float price, Float rating,
            String type, String diet, Integer stock) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id " + foodId));

        if (name != null)
            food.setName(name);
        if (price != null)
            food.setPrice(price);
        if (rating != null)
            food.setRating(rating);
        if (type != null)
            food.setType(type);
        if (diet != null)
            food.setDiet(diet);
        if (stock != null)
            food.setStock(stock);

        if (restaurantId != null) {
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Restaurant not found with id " + restaurantId));
            food.setRestaurant(restaurant);
        }

        foodRepository.save(food);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Food updated successfully!");
        return response;
    }

    public List<FoodResponseDto> getFoodByRestaurantId(Long restaurantId) {
        return foodRepository.findByRestaurant_Id(restaurantId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodResponseDto> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FoodResponseDto getFoodById(Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id " + foodId));
        return convertToDto(food);
    }

    public List<FoodResponseDto> getFoodByDiet(String diet) {
        return foodRepository.findByDiet(diet).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodResponseDto> getFoodByType(String type) {
        return foodRepository.findByType(type).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, String> deleteFood(Long foodId, DeleteCredentialsDto deleteCredentialsDto) {
        Long userId = deleteCredentialsDto.getUserId();
        String password = deleteCredentialsDto.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such user exists with id " + userId));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such food exists with id " + foodId));

        if (passwordEncoder.matches(password, user.getPassword())) {
            foodRepository.delete(food);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Food successfully deleted with Food Id " + food.getId());
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
        }
    }

}

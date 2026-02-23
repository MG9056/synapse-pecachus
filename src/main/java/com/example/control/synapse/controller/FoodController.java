package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.FoodRequest;
import com.example.control.synapse.dto.request.FoodUpdateDto;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.service.interfaces.IFoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final IFoodService foodService;

    @GetMapping("/allFoods")
    public List<FoodResponseDto> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public FoodResponseDto getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long restaurantId) {
        return foodService.getFoodByRestaurantId(restaurantId);
    }

    @GetMapping("/diet/{diet}")
    public List<FoodResponseDto> getFoodByDiet(@PathVariable String diet) {
        return foodService.getFoodByDiet(diet);
    }

    @GetMapping("/type/{type}")
    public List<FoodResponseDto> getFoodByType(@PathVariable String type) {
        return foodService.getFoodByType(type);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadFood(@RequestBody FoodRequest foodRequest) {
        return foodService.uploadFood(
                foodRequest.getName(),
                foodRequest.getRestaurantId(),
                foodRequest.getPrice(),
                foodRequest.getRating(),
                foodRequest.getType(),
                foodRequest.getDiet(),
                foodRequest.getStock());
    }

    @PatchMapping("/{id}")
    public Map<String, String> updateFood(@PathVariable Long id, @RequestBody FoodUpdateDto foodUpdateDto) {
        return foodService.updateFood(
                id,
                foodUpdateDto.getName(),
                foodUpdateDto.getRestaurantId(),
                foodUpdateDto.getPrice(),
                foodUpdateDto.getRating(),
                foodUpdateDto.getType(),
                foodUpdateDto.getDiet(),
                foodUpdateDto.getStock());
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteFood(@PathVariable Long id,
            @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return foodService.deleteFood(id, deleteCredentialsDto);
    }

}

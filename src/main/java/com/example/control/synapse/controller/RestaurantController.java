package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.RestaurantRequest;
import com.example.control.synapse.dto.request.RestaurantUpdateDto;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.service.interfaces.IFoodService;
import com.example.control.synapse.service.interfaces.IRestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantService restaurantService;
    private final IFoodService foodService;

    @GetMapping("/allRestaurants")
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantResponseDto getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/{id}/menu")
    public List<FoodResponseDto> getFoodByRestaurantId(@PathVariable Long id) {
        return foodService.getFoodByRestaurantId(id);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<RestaurantResponseDto> getRestaurantByStadiumId(@PathVariable Long stadiumId) {
        return restaurantService.getRestaurantByStadiumId(stadiumId);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.uploadRestaurant(
                restaurantRequest.getName(),
                restaurantRequest.getRating(),
                restaurantRequest.getStadiumId());
    }

    @PatchMapping("/{id}")
    public Map<String, String> updateRestaurant(@PathVariable Long id,
            @RequestBody RestaurantUpdateDto restaurantUpdateDto) {
        return restaurantService.updateRestaurant(
                id,
                restaurantUpdateDto.getName(),
                restaurantUpdateDto.getRating(),
                restaurantUpdateDto.getStadiumId());
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteRestaurant(@PathVariable Long id,
            @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return restaurantService.deleteRestaurant(id, deleteCredentialsDto);
    }

}

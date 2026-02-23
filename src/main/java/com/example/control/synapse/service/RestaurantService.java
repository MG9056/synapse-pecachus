package com.example.control.synapse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.service.interfaces.IRestaurantService;

@Service
public class RestaurantService implements IRestaurantService {

    private final StadiumRepository stadiumRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RestaurantService(StadiumRepository stadiumRepository, RestaurantRepository restaurantRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.stadiumRepository = stadiumRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private RestaurantResponseDto convertToDto(Restaurant restaurant) {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setRating(restaurant.getRating());
        dto.setStadiumId(restaurant.getStadium() != null ? restaurant.getStadium().getId() : null);
        return dto;
    }

    @Transactional
    public Map<String, String> uploadRestaurant(String name, double rating, Long stadiumId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setRating(rating);

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        restaurant.setStadiumId(stadium);
        restaurantRepository.save(restaurant);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Restaurant uploaded successfully!");
        return response;
    }

    public RestaurantResponseDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id " + id));
        return convertToDto(restaurant);
    }

    public List<RestaurantResponseDto> getRestaurantByStadiumId(Long stadiumId) {
        return restaurantRepository.findByStadium_Id(stadiumId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, String> updateRestaurant(Long restaurantId, String name, Double rating, Long stadiumId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restaurant not found with id " + restaurantId));

        if (name != null)
            restaurant.setName(name);
        if (rating != null)
            restaurant.setRating(rating);

        if (stadiumId != null) {
            Stadium stadium = stadiumRepository.findById(stadiumId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Stadium not found with id " + stadiumId));
            restaurant.setStadiumId(stadium);
        }

        restaurantRepository.save(restaurant);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Restaurant updated successfully!");
        return response;
    }

    @Transactional
    public Map<String, String> deleteRestaurant(Long restaurantId, DeleteCredentialsDto deleteCredentialsDto) {
        Long userId = deleteCredentialsDto.getUserId();
        String password = deleteCredentialsDto.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such user exists with id " + userId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such restaurant exists with id " + restaurantId));

        if (passwordEncoder.matches(password, user.getPassword())) {
            restaurantRepository.delete(restaurant);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Restaurant successfully deleted with Restaurant Id " + restaurant.getId());
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
        }
    }

}

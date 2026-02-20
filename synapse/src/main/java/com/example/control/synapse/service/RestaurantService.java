package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;

@Service
public class RestaurantService {

    public StadiumRepository stadiumRepository;
    public RestaurantRepository restaurantRepository;
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public RestaurantService(StadiumRepository stadiumRepository, RestaurantRepository restaurantRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {this.stadiumRepository=stadiumRepository;
    this.restaurantRepository=restaurantRepository;
    this.userRepository= userRepository;
    this.passwordEncoder= passwordEncoder;


    }

    public Map<String,String> uploadRestaurant(String name, double rating, Long stadiumId)
    {Restaurant restaurant= new Restaurant();

    
        restaurant.setName(name);
        restaurant.setRating(rating);

        Stadium stadium= stadiumRepository.findById(stadiumId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));

        restaurant.setStadiumId(stadium);
        restaurantRepository.save(restaurant);

    

 Map<String,String> response = new HashMap<>();
        response.put("message", "Restaurant uploaded successfully!");
        return response;


    }

    public RestaurantResponseDto getRestaurantById(Long id)
    {RestaurantResponseDto restaurantResponseDto= new RestaurantResponseDto();

    Restaurant restaurant= restaurantRepository.findById(id).orElseThrow();
    
    restaurantResponseDto.setId(restaurant.getId());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setStadiumId(restaurant.getStadiumId().getId());

    return restaurantResponseDto;

    
    


    }

    public List<RestaurantResponseDto> getRestaurantByStadiumId(Long stadiumId)
    {List<RestaurantResponseDto> dtoList= new ArrayList<>();

        List<Restaurant> restaurants= restaurantRepository.findByStadiumId(stadiumId);

        for(Restaurant restaurant: restaurants)
        {RestaurantResponseDto restaurantResponseDto= new RestaurantResponseDto();

            restaurantResponseDto.setId(restaurant.getId());
            restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setStadiumId(restaurant.getStadiumId().getId());

    dtoList.add(restaurantResponseDto);








        }
        return dtoList;



    }


    public Map<String,String> updateRestaurant(Long restaurantId, String name, double rating, Long stadiumId)
    { Restaurant restaurant= restaurantRepository.findById(restaurantId).orElseThrow();

        restaurant.setName(name);
        restaurant.setRating(rating);

        Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();
        restaurant.setStadiumId(stadium);

        restaurantRepository.save(restaurant);

         Map<String,String> response = new HashMap<>();
        response.put("message", "Restaurant updated successfully!");
        return response;






        
    }


    public Map<String,String> deleteRestaurant(Long userId,String password, Long restaurantId) {
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("No such user with exists with id"+userId));
        Restaurant restaurant= restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("No such stadium with exists with id"+restaurantId));
        if (passwordEncoder.matches(password, user.getPassword())) {
            restaurantRepository.delete(restaurant);
            response.put("message","Restaurant successfully deleted with Restaurant Id"+restaurant.getId());
            
        } else {
            response.put("message","Password did not match");
        }
        return response;
    }
    
}

package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;

@Service
public class RestaurantService {

    private final StadiumRepository stadiumRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

       
        Stadium stadium = stadiumRepository.findById(stadiumId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stadium not found with id " + stadiumId));


        restaurant.setStadiumId(stadium);
        restaurantRepository.save(restaurant);

    

 Map<String,String> response = new HashMap<>();
        response.put("message", "Restaurant uploaded successfully!");
        return response;


    }

    public RestaurantResponseDto getRestaurantById(Long id)
    {RestaurantResponseDto restaurantResponseDto= new RestaurantResponseDto();

    Restaurant restaurant = restaurantRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id " + id));
    
    restaurantResponseDto.setId(restaurant.getId());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setStadiumId(restaurant.getStadium().getId());

    return restaurantResponseDto;

    
    


    }

    public List<RestaurantResponseDto> getRestaurantByStadiumId(Long stadiumId)
    {List<RestaurantResponseDto> dtoList= new ArrayList<>();

        List<Restaurant> restaurants= restaurantRepository.findByStadium_Id(stadiumId);

        for(Restaurant restaurant: restaurants)
        {RestaurantResponseDto restaurantResponseDto= new RestaurantResponseDto();

            restaurantResponseDto.setId(restaurant.getId());
            restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setName(restaurant.getName());
    restaurantResponseDto.setStadiumId(restaurant.getStadium().getId());

    dtoList.add(restaurantResponseDto);








        }
        return dtoList;



    }


    public Map<String,String> updateRestaurant(Long restaurantId, String name, Double rating, Long stadiumId)
    { Restaurant restaurant = restaurantRepository.findById(restaurantId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id " + restaurantId));
       
       if(name!=null)
        {restaurant.setName(name);}

       if(rating!=null)
       { restaurant.setRating(rating);}

       if(stadiumId!=null)
       {Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stadium not found with id " + stadiumId));
        restaurant.setStadiumId(stadium);}

        restaurantRepository.save(restaurant);

         Map<String,String> response = new HashMap<>();
        response.put("message", "Restaurant updated successfully!");
        return response;






        
    }


    public Map<String,String> deleteRestaurant(Long restaurantId,DeleteCredentialsDto deleteCredentialsDto) {
Long userId= deleteCredentialsDto.getUserId();
String password= deleteCredentialsDto.getPassword();

        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists with id " + userId));

         Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such restaurant exists with id " + restaurantId));

        if (passwordEncoder.matches(password, user.getPassword())) {
            restaurantRepository.delete(restaurant);
            response.put("message", "Restaurant successfully deleted with Restaurant Id " + restaurant.getId());
        } else {
            // FIX 5: wrong password now throws 401 instead of returning 200
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
        }


        return response;
    }
    
}

package com.example.control.synapse.service;

import org.springframework.stereotype.Service;

import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;

@Service
public class RestaurantService {

    public StadiumRepository stadiumRepository;
    public RestaurantRepository restaurantRepository;

    public RestaurantService(StadiumRepository stadiumRepository, RestaurantRepository restaurantRepository)
    {this.stadiumRepository=stadiumRepository;
    this.restaurantRepository=restaurantRepository;

    }

    public String uploadRestaurant(String name, double rating, Long stadiumId)
    {Restaurant restaurant= new Restaurant();

    
        restaurant.setName(name);
        restaurant.setRating(rating);

        Stadium stadium= stadiumRepository.findById(stadiumId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));

        restaurant.setStadiumId(stadium);
        restaurantRepository.save(restaurant);

    


return "Restaurant uploaded!";


    }
    
}

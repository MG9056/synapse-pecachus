package com.example.control.synapse.service;

import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.StadiumRepository;

public class RestaurantService {

    public StadiumRepository stadiumRepository;

    public RestaurantService(StadiumRepository stadiumRepository)
    {this.stadiumRepository=stadiumRepository;

    }

    public String uploadRestaurant(String name, double rating, Long stadiumId)
    {Restaurant restaurant= new Restaurant();

    
        restaurant.setName(name);
        restaurant.setRating(rating);

        Stadium stadium= stadiumRepository.findById(stadiumId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));

        restaurant.setStadiumId(stadium);

    


return "Restaurant uploaded!";


    }
    
}

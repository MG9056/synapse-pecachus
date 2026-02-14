package com.example.control.synapse.service;

import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;

public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final RestaurantRepository restaurantRepository;

    public StadiumService(StadiumRepository stadiumRepository, RestaurantRepository restaurantRepository)
    {
        this.stadiumRepository=stadiumRepository;
        this.restaurantRepository= restaurantRepository;
    }

    




    public String uploadStadium(String city, String state, String country, int capacity)
    {
        Stadium stadium= new Stadium();
        stadium.setCity(city);
        stadium.setState(state);
        stadium.setCountry(country);
        stadium.setCapacity(capacity);

        stadiumRepository.save(stadium);

        return "Restaurant uploaded!";


        


    }

    public String updateStadium(Long stadiumId, String city, String state, String country, int capacity )
    { Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();

     stadium.setCity(city);
     stadium.setState(state);
     stadium.setCountry(country);
     stadium.setCapacity(capacity);


     stadiumRepository.save(stadium);




        return "Stadium updated successfully!";


    }

    
    
}
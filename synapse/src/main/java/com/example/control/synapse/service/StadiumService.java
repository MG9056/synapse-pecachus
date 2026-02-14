package com.example.control.synapse.service;

import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.StadiumRepository;

public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public StadiumService(StadiumRepository stadiumRepository)
    {
        this.stadiumRepository=stadiumRepository;
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
    
}
package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;

import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
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

    public StadiumResponseDto getStadiumById(Long stadiumId)
    {Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();

        StadiumResponseDto stadiumResponseDto= new StadiumResponseDto();

        stadiumResponseDto.setCity(stadium.getCity());
     stadiumResponseDto.setState(stadium.getState());
     stadiumResponseDto.setCountry(stadium.getCountry());
     stadiumResponseDto.setCapacity(stadium.getCapacity());

     return stadiumResponseDto;


        
    }

    public List<StadiumResponseDto> getAllStadiums()
    {List<Stadium> stadiums= stadiumRepository.findAll();

        List<StadiumResponseDto> dtoList= new ArrayList<>();

        for(Stadium stadium: stadiums)
        {StadiumResponseDto stadiumResponseDto= new StadiumResponseDto();

            stadiumResponseDto.setCity(stadium.getCity());
     stadiumResponseDto.setState(stadium.getState());
     stadiumResponseDto.setCountry(stadium.getCountry());
     stadiumResponseDto.setCapacity(stadium.getCapacity());

     dtoList.add(stadiumResponseDto);






        }

        return dtoList;



        
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
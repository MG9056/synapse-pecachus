package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;

import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;

import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;


@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StadiumService(StadiumRepository stadiumRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.stadiumRepository=stadiumRepository;

        this.userRepository= userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    




    public Map<String,String> uploadStadium(String city, String state, String country, Integer capacity)
    {
        Stadium stadium= new Stadium();
        stadium.setCity(city);
        stadium.setState(state);
        stadium.setCountry(country);
        stadium.setCapacity(capacity);

        stadiumRepository.save(stadium);

        Map<String,String> response = new HashMap<>();
        response.put("message", "Stadium uploaded successfully!");
        return response;


        


    }

    public StadiumResponseDto getStadiumById(Long stadiumId)
    {Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();

        StadiumResponseDto stadiumResponseDto= new StadiumResponseDto();

        stadiumResponseDto.setId(stadium.getId());
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

            stadiumResponseDto.setId(stadium.getId());
            stadiumResponseDto.setCity(stadium.getCity());
     stadiumResponseDto.setState(stadium.getState());
     stadiumResponseDto.setCountry(stadium.getCountry());
     stadiumResponseDto.setCapacity(stadium.getCapacity());

     dtoList.add(stadiumResponseDto);






        }

        return dtoList;



        
    }

    public Map<String,String> updateStadium(Long stadiumId, String city, String state, String country, Integer capacity )
    { Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();



   if (city != null)
    stadium.setCity(city);

if (state != null)
    stadium.setState(state);

if (country != null)
    stadium.setCountry(country);

if (capacity != null)
    stadium.setCapacity(capacity);
    


     stadiumRepository.save(stadium);




         Map<String,String> response = new HashMap<>();
        response.put("message", "Stadium updated successfully!");
        return response;


    }


    public Map<String,String> deleteStadium(Long stadiumId, DeleteCredentialsDto deleteCredentialsDto) {
        
        Long userId= deleteCredentialsDto.getUserId();
        String password= deleteCredentialsDto.getPassword();

        
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("No such user with exists with id"+userId));
        Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow(()-> new RuntimeException("No such stadium with exists with id"+stadiumId));
        if (passwordEncoder.matches(password, user.getPassword())) {
            stadiumRepository.delete(stadium);
            response.put("message","Stadium successfully deleted with Stadium Id"+stadium.getId());
            
        } else {
            response.put("message","Password did not match");
        }
        return response;
    }

     

    
    
}
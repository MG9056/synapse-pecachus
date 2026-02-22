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
import com.example.control.synapse.dto.response.MerchandiseResponseDto;

import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;

@Service
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StadiumRepository stadiumRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, StadiumRepository stadiumRepository)
    {
        this.merchandiseRepository= merchandiseRepository;
        this.userRepository= userRepository;
        this.passwordEncoder= passwordEncoder;
        this.stadiumRepository= stadiumRepository;
    }

    public MerchandiseResponseDto getMerchandiseById(Long id)
    {Merchandise merchandise = merchandiseRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchandise not found with id " + id));

        MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            

            merchandiseResponseDto.setId(merchandise.getId());
            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadium().getId());

        return merchandiseResponseDto;





    }


    public List<MerchandiseResponseDto> getAllMerchandise()
    {List<Merchandise> merchandises= merchandiseRepository.findAll();
        List<MerchandiseResponseDto> dtoList= new ArrayList<>();

        for(Merchandise merchandise: merchandises)
        {MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            
            merchandiseResponseDto.setId(merchandise.getId());
            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadium().getId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    



    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId )
    {List<Merchandise> merchandises= merchandiseRepository.findByStadium_Id(stadiumId);
        List<MerchandiseResponseDto> dtoList= new ArrayList<>();

        for(Merchandise merchandise: merchandises)
        {MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            
            merchandiseResponseDto.setId(merchandise.getId());

            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadium().getId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    public Map<String,String> uploadMerchandise(String name, String description, double price, double rating, Long stadiumId, String size, String type, Integer stock)
    {
        Merchandise merchandise= new Merchandise();

        merchandise.setName(name);
        merchandise.setDescription(description);
        merchandise.setPrice(price);
        merchandise.setRating(rating);
        merchandise.setSize(size);
        merchandise.setType(type);
        merchandise.setStock(stock);

      Stadium stadium = stadiumRepository.findById(stadiumId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stadium not found with id " + stadiumId));

        merchandise.setStadium(stadium);

        merchandiseRepository.save(merchandise);

        Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise uploaded successfully!");
        return response;



        
    }


    public Map<String, String> updateMerchandise(Long merchandiseId, String name, String description, Double price, Double rating, Long stadiumId, String type, String size, Integer stock)
    {Merchandise merchandise = merchandiseRepository.findById(merchandiseId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchandise not found with id " + merchandiseId));

     if (name != null) {
    merchandise.setName(name);
}
if (description != null) {
    merchandise.setDescription(description);
}
if (price != null) {
    merchandise.setPrice(price);
}
if (rating != null) {
    merchandise.setRating(rating);
}
if (type != null) {
    merchandise.setType(type);
}
if (size != null) {
    merchandise.setSize(size);
}
if (stock != null) {
    merchandise.setStock(stock);
}

if(stadiumId!=null)
     {Stadium stadium = stadiumRepository.findById(stadiumId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stadium not found with id " + stadiumId));
     merchandise.setStadium(stadium);}

     merchandiseRepository.save(merchandise);

     Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise updated successfully!");
        return response;

     




    }


    public Map<String,String> deleteMerchandise(Long merchandiseId,DeleteCredentialsDto deleteCredentialsDto) {
        
        Long userId= deleteCredentialsDto.getUserId();
        String password=deleteCredentialsDto.getPassword();

        Map<String,String> response = new HashMap<>();

       User user = userRepository.findById(userId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists with id " + userId));

Merchandise merchandise = merchandiseRepository.findById(merchandiseId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such merchandise exists with id " + merchandiseId));


       if (passwordEncoder.matches(password, user.getPassword())) {
    merchandiseRepository.delete(merchandise);
    response.put("message", "Merchandise successfully deleted with Merchandise Id " + merchandise.getId());
} else {
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
}
        return response;
    }
}

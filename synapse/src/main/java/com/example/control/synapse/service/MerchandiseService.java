package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.request.MerchandiseRequest;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.models.Food;
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
    {Merchandise merchandise= merchandiseRepository.findById(id).orElseThrow();

        MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            

            merchandiseResponseDto.setId(merchandise.getId());
            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId().getId());

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
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId().getId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    



    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId )
    {List<Merchandise> merchandises= merchandiseRepository.findByStadiumId(stadiumId);
        List<MerchandiseResponseDto> dtoList= new ArrayList<>();

        for(Merchandise merchandise: merchandises)
        {MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            
            merchandiseResponseDto.setId(merchandise.getId());

            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId().getId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    public Map<String,String> uploadMerchandise(String name, String description, double price, double rating, Long stadiumId)
    {
        Merchandise merchandise= new Merchandise();

        merchandise.setName(name);
        merchandise.setDescription(description);
        merchandise.setPrice(price);
        merchandise.setRating(rating);

        Stadium stadium= stadiumRepository.findById(stadiumId).orElseThrow();
        merchandise.setStadiumId(stadium);

        merchandiseRepository.save(merchandise);

        Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise uploaded successfully!");
        return response;



        
    }


    public Map<String, String> updateMerchandise(Long merchandiseId, String name, String description, double price, double rating, Stadium stadiumId)
    {Merchandise merchandise= merchandiseRepository.findById(merchandiseId).orElseThrow();

     merchandise.setName(name);
     merchandise.setName(description);
     merchandise.setPrice(price);
     merchandise.setRating(rating);
     merchandise.setStadiumId(stadiumId);

     merchandiseRepository.save(merchandise);

     Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise updated successfully!");
        return response;

     




    }


    public Map<String,String> deleteMerchandise(Long userId,String password, Long merchandiseId) {
        Map<String,String> response = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("No such user with exists with id"+userId));
        Merchandise merchandise= merchandiseRepository.findById(merchandiseId).orElseThrow(()-> new RuntimeException("No such merchandise with exists with id"+merchandiseId));
        if (passwordEncoder.matches(password, user.getPassword())) {
            merchandiseRepository.delete(merchandise);
            response.put("message","Merchandise successfully deleted with Merchandise Id"+merchandise.getId());
            
        } else {
            response.put("message","Password did not match");
        }
        return response;
    }
}

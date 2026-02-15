package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.request.MerchandiseRequest;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.MerchandiseRepository;

@Service
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository)
    {
        this.merchandiseRepository= merchandiseRepository;
    }

    public MerchandiseResponseDto getMerchandiseById(Long id)
    {Merchandise merchandise= merchandiseRepository.findById(id).orElseThrow();

        MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            

            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId());

        return merchandiseResponseDto;





    }


    public List<MerchandiseResponseDto> getAllMerchandise()
    {List<Merchandise> merchandises= merchandiseRepository.findAll();
        List<MerchandiseResponseDto> dtoList= new ArrayList<>();

        for(Merchandise merchandise: merchandises)
        {MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            

            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    



    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId )
    {List<Merchandise> merchandises= merchandiseRepository.findByStadiumId(stadiumId);
        List<MerchandiseResponseDto> dtoList= new ArrayList<>();

        for(Merchandise merchandise: merchandises)
        {MerchandiseResponseDto merchandiseResponseDto= new MerchandiseResponseDto();

            

            merchandiseResponseDto.setName(merchandise.getName());
            merchandiseResponseDto.setDescription(merchandise.getDescription());
            merchandiseResponseDto.setPrice(merchandise.getPrice());
            merchandiseResponseDto.setRating(merchandise.getRating());
            merchandiseResponseDto.setStadiumId(merchandise.getStadiumId());

        dtoList.add(merchandiseResponseDto);



        }

        return dtoList;
    }

    public Map<String,String> uploadMerchandise(String name, String description, double price, double rating, Stadium stadiumId)
    {
        Merchandise merchandise= new Merchandise();

        merchandise.setName(name);
        merchandise.setDescription(description);
        merchandise.setPrice(price);
        merchandise.setRating(rating);
        merchandise.setStadiumId(stadiumId);

        Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise uploaded successfully!");
        return response;



        
    }
}

package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.repository.MerchandiseRepository;

@Service
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository)
    {
        this.merchandiseRepository= merchandiseRepository;
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

}

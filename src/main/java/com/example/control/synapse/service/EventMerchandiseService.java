package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;
import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.repository.EventMerchandiseRepository;


@Service
public class EventMerchandiseService {

    private final EventMerchandiseRepository eventMerchandiseRepository;

    public EventMerchandiseService(EventMerchandiseRepository eventMerchandiseRepository)
    {
        this.eventMerchandiseRepository= eventMerchandiseRepository;
    }

    public EventMerchandiseResponseDto getEventMerchandiseById(Long merchandiseId)
    {EventMerchandise eventMerchandise = eventMerchandiseRepository.findById(merchandiseId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventMerchandise not found with id " + merchandiseId));

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setId(eventMerchandise.getId());
        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadium().getId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrder().getId());

        return eventMerchandiseResponseDto;
    
    
    
    
    }


    public List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(Long merchandiseOrderId)
    {
        List<EventMerchandise> eventMerchandises= eventMerchandiseRepository.findByMerchandiseOrder_Id(merchandiseOrderId);

        List<EventMerchandiseResponseDto> dtoList=new ArrayList<>();

        for(EventMerchandise eventMerchandise: eventMerchandises)
        {

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setId(eventMerchandise.getId());
        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadium().getId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrder().getId());

        dtoList.add(eventMerchandiseResponseDto);
        }

        return dtoList;
    
    
    
    
    }

    public List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(Long stadiumId)
    {
        List<EventMerchandise> eventMerchandises= eventMerchandiseRepository.findByStadium_Id(stadiumId);

        List<EventMerchandiseResponseDto> dtoList=new ArrayList<>();

        for(EventMerchandise eventMerchandise: eventMerchandises)
        {

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setId(eventMerchandise.getId());
        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadium().getId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrder().getId());

        dtoList.add(eventMerchandiseResponseDto);
        }

        return dtoList;
    
    
    
    
    }

    public List<EventMerchandiseResponseDto> getAllEventMerchandise()
    {
        List<EventMerchandise> eventMerchandises= eventMerchandiseRepository.findAll();

        List<EventMerchandiseResponseDto> dtoList=new ArrayList<>();

        for(EventMerchandise eventMerchandise: eventMerchandises)
        {

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();
       

        eventMerchandiseResponseDto.setId(eventMerchandise.getId());
        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadium().getId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrder().getId());

        dtoList.add(eventMerchandiseResponseDto);
        }

        return dtoList;
    
    
    
    
    }

    
    
}

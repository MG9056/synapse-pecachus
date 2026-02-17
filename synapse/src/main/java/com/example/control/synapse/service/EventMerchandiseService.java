package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;
import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.repository.EventMerchandiseRepository;

public class EventMerchandiseService {

    private final EventMerchandiseRepository eventMerchandiseRepository;

    public EventMerchandiseService(EventMerchandiseRepository eventMerchandiseRepository)
    {
        this.eventMerchandiseRepository= eventMerchandiseRepository;
    }

    public EventMerchandiseResponseDto getEventMerchandiseById(Long merchandiseId)
    {
        EventMerchandise eventMerchandise= eventMerchandiseRepository.findById(merchandiseId).orElseThrow();

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadiumId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrderId());

        return eventMerchandiseResponseDto;
    
    
    
    
    }


    public List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(Long merchandiseOrderId)
    {
        List<EventMerchandise> eventMerchandises= eventMerchandiseRepository.findByMerchandiseOrderId(merchandiseOrderId);

        List<EventMerchandiseResponseDto> dtoList=new ArrayList<>();

        for(EventMerchandise eventMerchandise: eventMerchandises)
        {

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadiumId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrderId());

        dtoList.add(eventMerchandiseResponseDto);
        }

        return dtoList;
    
    
    
    
    }

    public List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(Long stadiumId)
    {
        List<EventMerchandise> eventMerchandises= eventMerchandiseRepository.findByMerchandiseOrderId(stadiumId);

        List<EventMerchandiseResponseDto> dtoList=new ArrayList<>();

        for(EventMerchandise eventMerchandise: eventMerchandises)
        {

        EventMerchandiseResponseDto  eventMerchandiseResponseDto= new EventMerchandiseResponseDto();

        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadiumId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrderId());

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

        eventMerchandiseResponseDto.setName(eventMerchandise.getName());
        eventMerchandiseResponseDto.setDescription(eventMerchandise.getDescription());
        eventMerchandiseResponseDto.setPrice(eventMerchandise.getPrice());
        eventMerchandiseResponseDto.setRating(eventMerchandise.getRating());
        eventMerchandiseResponseDto.setStadiumId(eventMerchandise.getStadiumId());
        eventMerchandiseResponseDto.setMerchandiseOrderId(eventMerchandise.getMerchandiseOrderId());

        dtoList.add(eventMerchandiseResponseDto);
        }

        return dtoList;
    
    
    
    
    }

    
    
}

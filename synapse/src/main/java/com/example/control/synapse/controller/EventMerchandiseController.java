package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;
import com.example.control.synapse.service.EventMerchandiseService;

@RestController
@RequestMapping("/EventMerchandise")
public class EventMerchandiseController {

    private EventMerchandiseService eventMerchandiseService;

    public EventMerchandiseController(EventMerchandiseService eventMerchandiseService)
    {
        this.eventMerchandiseService= eventMerchandiseService;
    }

    @GetMapping
    public List<EventMerchandiseResponseDto> getAllEventMerchandise()
    {
        return eventMerchandiseService.getAllEventMerchandise();
    }
    

    @GetMapping("/{id}")
    public EventMerchandiseResponseDto getEventMerchandiseById(@PathVariable Long id)
    {
        return eventMerchandiseService.getEventMerchandiseById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(@PathVariable Long orderId)
    {
        return eventMerchandiseService.getEventMerchandiseByMerchandiseOrderId(orderId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(@PathVariable Long stadiumId)
    { 
        return eventMerchandiseService.getEventMerchandiseByStadiumId(stadiumId);

    }


    
}


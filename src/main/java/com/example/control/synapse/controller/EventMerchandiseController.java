package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.repository.EventMerchandiseRepository;


@RestController
@RequestMapping("/eventMerchandise")
public class EventMerchandiseController {

    private final EventMerchandiseRepository eventMerchandiseRepository;



    public EventMerchandiseController( EventMerchandiseRepository eventMerchandiseRepository)
    {
      
        this.eventMerchandiseRepository = eventMerchandiseRepository;
    }

    @GetMapping("/allEventMerchandise")
    public List<EventMerchandise> getAllEventMerchandise()
    {
        return eventMerchandiseRepository.findAll();
    }
    

    @GetMapping("/{id}")
    public EventMerchandise getEventMerchandiseById(@PathVariable Long id)
    {
        return eventMerchandiseRepository.findById(id).orElseThrow();
    }

    @GetMapping("/order/{orderId}")
    public List<EventMerchandise> getEventMerchandiseByMerchandiseOrderId(@PathVariable Long orderId)
    {
        return eventMerchandiseRepository.findByMerchandiseOrder_Id(orderId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<EventMerchandise> getEventMerchandiseByStadiumId(@PathVariable Long stadiumId)
    { 
        return eventMerchandiseRepository.findByStadium_Id(stadiumId);

    }
    @GetMapping("/event/{eventId}")
    public List<EventMerchandise> getEventMerchandiseByEventId(@PathVariable Long eventId)
    { 
        return eventMerchandiseRepository.findByEvent_Id(eventId);

    }


    
}


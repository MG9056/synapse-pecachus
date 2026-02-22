package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.models.EventFood;
import com.example.control.synapse.repository.EventFoodRepository;


@RestController
@RequestMapping("/eventfood")
public class EventFoodController {

    private final EventFoodRepository eventFoodRepository;



    public EventFoodController( EventFoodRepository eventFoodRepository)
    {
       
        this.eventFoodRepository = eventFoodRepository;
    }

    @GetMapping("/allEventFoods")
    public List<EventFood> getAllEventFood()
    {
        return eventFoodRepository.findAll();
    }
    





    @GetMapping("/{id}")
    public EventFood getEventFoodById(@PathVariable Long id)
    {
        return eventFoodRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventFood not found with id " + id));
    }

    @GetMapping("/order/{orderId}")
    public List<EventFood> getEventFoodByOrderId(@PathVariable Long orderId)
    {
        return eventFoodRepository.findByOrder_Id(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<EventFood> getEventFoodByRestaurantId(@PathVariable Long restaurantId)
    { 
        return eventFoodRepository.findByRestaurant_Id(restaurantId);

    }

    @GetMapping("/event/{eventId}")
    public List<EventFood> getEventFoodByEventId(@PathVariable Long eventId)
    { 
        return eventFoodRepository.findByEvent_Id(eventId);

    }





    
}

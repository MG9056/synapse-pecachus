package com.example.control.synapse.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.control.synapse.dto.response.EventFoodResponseDto;
import com.example.control.synapse.service.EventFoodService;

@RestController
@RequestMapping("/eventfood")
public class EventFoodController {

    private EventFoodService eventFoodService;

    public EventFoodController(EventFoodService eventFoodService)
    {
        this.eventFoodService= eventFoodService;
    }

    @GetMapping("/allEventFoods")
    public List<EventFoodResponseDto> getAllEventFood()
    {
        return eventFoodService.getAllEventFood();
    }
    





    @GetMapping("/{id}")
    public EventFoodResponseDto getEventFoodById(@PathVariable Long id)
    {
        return eventFoodService.getEventFoodById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<EventFoodResponseDto> getEventFoodByOrderId(@PathVariable Long orderId)
    {
        return eventFoodService.getEventFoodByOrderId(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<EventFoodResponseDto> getEventFoodByRestaurantId(@PathVariable Long restaurantId)
    { 
        return eventFoodService.getEventFoodByRestaurantId(restaurantId);

    }


    
}

package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.response.EventFoodResponseDto;
import com.example.control.synapse.service.interfaces.IEventFoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventfood")
@RequiredArgsConstructor
public class EventFoodController {

    private final IEventFoodService eventFoodService;

    @GetMapping("/allEventFoods")
    public List<EventFoodResponseDto> getAllEventFood() {
        return eventFoodService.getAllEventFood();
    }

    @GetMapping("/{id}")
    public EventFoodResponseDto getEventFoodById(@PathVariable Long id) {
        return eventFoodService.getEventFoodById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<EventFoodResponseDto> getEventFoodByOrderId(@PathVariable Long orderId) {
        return eventFoodService.getEventFoodByOrderId(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<EventFoodResponseDto> getEventFoodByRestaurantId(@PathVariable Long restaurantId) {
        return eventFoodService.getEventFoodByRestaurantId(restaurantId);
    }

    @GetMapping("/event/{eventId}")
    public List<EventFoodResponseDto> getEventFoodByEventId(@PathVariable Long eventId) {
        return eventFoodService.getEventFoodByEventId(eventId);
    }

}

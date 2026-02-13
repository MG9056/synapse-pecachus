package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.service.EventService;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/events")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @GetMapping
    public EventPageResponseDto getAllEvents(Pageable pageable,@RequestParam(required = false) String category,
            @RequestParam(required = false) String city,@RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice ) {

    }
    
}

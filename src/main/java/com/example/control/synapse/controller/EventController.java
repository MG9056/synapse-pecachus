package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.EventRequestDto;

import com.example.control.synapse.dto.response.EventResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.service.interfaces.IEventService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/events")
public class EventController {
    private IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents(@RequestParam(required = false) String category,
            @RequestParam(required = false) String city, @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return eventService.getAllEvents(category, city, minPrice, maxPrice);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createEvent(@RequestBody EventRequestDto request) {
        return eventService.createEvent(request.getName(), request.getDateTime(), request.getCategory(),
                request.getStadiumId(), request.getDescription(), request.getMinPrice(), request.getLive());
    }

    // READ ONE
    @GetMapping("/{id}")
    public EventResponseDto getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PatchMapping("/{id}")
    public Map<String, String> goEventLive(@PathVariable Long id) {
        return eventService.goEventLive(id);
    }

    @PatchMapping("/{id}/down")
    public Map<String, String> eventDown(@PathVariable Long id) {
        return eventService.goDown(id);
    }

}

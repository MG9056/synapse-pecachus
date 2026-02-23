package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;
import com.example.control.synapse.service.interfaces.IEventMerchandiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventmerchandise")
@RequiredArgsConstructor
public class EventMerchandiseController {

    private final IEventMerchandiseService eventMerchandiseService;

    @GetMapping("/allEventMerchandise")
    public List<EventMerchandiseResponseDto> getAllEventMerchandise() {
        return eventMerchandiseService.getAllEventMerchandise();
    }

    @GetMapping("/{id}")
    public EventMerchandiseResponseDto getEventMerchandiseById(@PathVariable Long id) {
        return eventMerchandiseService.getEventMerchandiseById(id);
    }

    @GetMapping("/order/{merchandiseOrderId}")
    public List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(
            @PathVariable Long merchandiseOrderId) {
        return eventMerchandiseService.getEventMerchandiseByMerchandiseOrderId(merchandiseOrderId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(@PathVariable Long stadiumId) {
        return eventMerchandiseService.getEventMerchandiseByStadiumId(stadiumId);
    }

    @GetMapping("/event/{eventId}")
    public List<EventMerchandiseResponseDto> getEventMerchandiseByEventId(@PathVariable Long eventId) {
        return eventMerchandiseService.getEventMerchandiseByEventId(eventId);
    }

}

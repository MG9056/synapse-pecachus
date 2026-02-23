package com.example.control.synapse.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.EventResponseDto;
import com.example.control.synapse.models.Event;

public interface IEventService {
    List<Event> getAllEvents(String category, String city, Double minPrice, Double maxPrice);

    Map<String, String> createEvent(String name, LocalDateTime dateTime, String category, Long stadiumId,
            String description, Double minPrice, Boolean live);

    EventResponseDto getEvent(Long id);

    Map<String, String> goEventLive(Long id);

    Map<String, String> goDown(Long id);
}

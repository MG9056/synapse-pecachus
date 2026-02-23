package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.EventResponseDto;
import com.example.control.synapse.mapper.EventMapper;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.specification.EventSpecification;
import com.example.control.synapse.service.interfaces.IEventService;

@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private final StadiumRepository stadiumRepository;

    public EventService(EventRepository eventRepository, StadiumRepository stadiumRepository) {
        this.eventRepository = eventRepository;
        this.stadiumRepository = stadiumRepository;
    }

    public List<Event> getAllEvents(String category, String city, Double minPrice, Double maxPrice) {
        Specification<Event> spec = EventSpecification.getEventsByFilters(category, city, minPrice, maxPrice);
        List<Event> events = eventRepository.findAll(spec);
        return events;
    }

    public Map<String, String> createEvent(String name, LocalDateTime dateTime, String category, Long stadiumId,
            String description,
            Double minPrice, Boolean live) {
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        Event event = new Event(stadium, name, dateTime, description, minPrice, category, live);
        eventRepository.save(event);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event created successfully");
        return response;
    }

    public EventResponseDto getEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("No such event found"));
        return EventMapper.toDto(event);
    }

    public Map<String, String> goEventLive(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("No such event found"));
        event.setLive(true);
        eventRepository.save(event);
        Map<String, String> a = new HashMap<>();
        a.put("message", "Event has gone live");
        return a;
    }

    public Map<String, String> goDown(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("No such event found"));
        event.setLive(false);
        eventRepository.save(event);
        Map<String, String> a = new HashMap<>();
        a.put("message", "Event has gone down");
        return a;
    }

}

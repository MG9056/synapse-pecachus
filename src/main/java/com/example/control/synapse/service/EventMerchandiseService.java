package com.example.control.synapse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;
import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.repository.EventMerchandiseRepository;
import com.example.control.synapse.service.interfaces.IEventMerchandiseService;

@Service
public class EventMerchandiseService implements IEventMerchandiseService {

    private final EventMerchandiseRepository eventMerchandiseRepository;

    public EventMerchandiseService(EventMerchandiseRepository eventMerchandiseRepository) {
        this.eventMerchandiseRepository = eventMerchandiseRepository;
    }

    private EventMerchandiseResponseDto convertToDto(EventMerchandise em) {
        EventMerchandiseResponseDto dto = new EventMerchandiseResponseDto();
        dto.setId(em.getId());
        dto.setName(em.getName());
        dto.setDescription(em.getDescription());
        dto.setPrice(em.getPrice());
        dto.setRating(em.getRating());
        dto.setStadiumId(em.getStadium() != null ? em.getStadium().getId() : null);
        dto.setMerchandiseOrderId(em.getMerchandiseOrder() != null ? em.getMerchandiseOrder().getId() : null);
        dto.setEventId(em.getEvent() != null ? em.getEvent().getId() : null);
        return dto;
    }

    public EventMerchandiseResponseDto getEventMerchandiseById(Long merchandiseId) {
        EventMerchandise em = eventMerchandiseRepository.findById(merchandiseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "EventMerchandise not found with id " + merchandiseId));
        return convertToDto(em);
    }

    public List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(Long merchandiseOrderId) {
        return eventMerchandiseRepository.findByMerchandiseOrder_Id(merchandiseOrderId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(Long stadiumId) {
        return eventMerchandiseRepository.findByStadium_Id(stadiumId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EventMerchandiseResponseDto> getAllEventMerchandise() {
        return eventMerchandiseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EventMerchandiseResponseDto> getEventMerchandiseByEventId(Long eventId) {
        return eventMerchandiseRepository.findByEvent_Id(eventId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

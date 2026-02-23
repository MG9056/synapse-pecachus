package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.EventSeatCreateDto;
import com.example.control.synapse.dto.response.EventSeatResponseDto;

public interface IEventSeatService {
    List<EventSeatResponseDto> createEventSeatsForEvent(Long eventId, Map<String, Double> categoryPriceMap);

    EventSeatResponseDto createEventSeat(EventSeatCreateDto createDto);

    EventSeatResponseDto getEventSeatById(Long id);

    List<EventSeatResponseDto> getAllEventSeats();

    List<EventSeatResponseDto> getEventSeatsByEventId(Long eventId);

    List<EventSeatResponseDto> getAvailableEventSeatsByEventId(Long eventId);

    EventSeatResponseDto updateEventSeat(Long id, EventSeatCreateDto updateDto);

    EventSeatResponseDto updateEventSeatAvailability(Long id, Boolean availability);

    void deleteEventSeat(Long id);
}

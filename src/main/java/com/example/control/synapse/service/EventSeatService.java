package com.example.control.synapse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.control.synapse.dto.request.EventSeatCreateDto;
import com.example.control.synapse.dto.response.EventSeatResponseDto;
import com.example.control.synapse.error.BusinessException;
import com.example.control.synapse.error.InvalidRequestException;
import com.example.control.synapse.error.ResourceNotFoundException;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.EventSeat;
import com.example.control.synapse.models.Seat;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.EventSeatRepository;
import com.example.control.synapse.repository.SeatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.control.synapse.service.interfaces.IEventSeatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventSeatService implements IEventSeatService {

    private final EventSeatRepository eventSeatRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;

    /**
     * Create event seats for all seats in a stadium when a new event is created
     * 
     * @param eventId          The ID of the newly created event
     * @param categoryPriceMap Map of seat category to price (e.g., {"VIP": 100.0,
     *                         "Regular": 50.0})
     * @return List of created event seats
     */
    @Transactional
    public List<EventSeatResponseDto> createEventSeatsForEvent(Long eventId, Map<String, Double> categoryPriceMap) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));

        // Get all seats for the stadium
        List<Seat> seats = seatRepository.findByStadiumId(event.getStadium().getId());

        if (seats.isEmpty()) {
            throw new BusinessException("No seats found for stadium with id: " + event.getStadium().getId());
        }

        List<EventSeat> eventSeats = new ArrayList<>();

        for (Seat seat : seats) {
            Double price = categoryPriceMap.get(seat.getCategory().toString());

            if (price == null) {
                throw new InvalidRequestException("Price not provided for seat category: " + seat.getCategory());
            }

            EventSeat eventSeat = new EventSeat();
            eventSeat.setSeat(seat);
            eventSeat.setEvent(event);
            eventSeat.setAvailability(true);
            eventSeat.setPrice(price);

            eventSeats.add(eventSeat);
        }

        List<EventSeat> savedEventSeats = eventSeatRepository.saveAll(eventSeats);

        return savedEventSeats.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Create a single event seat
     */
    @Transactional
    public EventSeatResponseDto createEventSeat(EventSeatCreateDto createDto) {

        // Check if event seat already exists
        if (eventSeatRepository.existsByEventIdAndSeatId(createDto.getEventId(), createDto.getSeatId())) {
            throw new IllegalStateException("Event seat already exists for this event and seat");
        }

        Seat seat = seatRepository.findById(createDto.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + createDto.getSeatId()));

        Event event = eventRepository.findById(createDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + createDto.getEventId()));

        EventSeat eventSeat = new EventSeat();
        eventSeat.setSeat(seat);
        eventSeat.setEvent(event);
        eventSeat.setAvailability(createDto.getAvailability());
        eventSeat.setPrice(createDto.getPrice());

        EventSeat savedEventSeat = eventSeatRepository.save(eventSeat);
        log.info("Event seat created with ID: {}", savedEventSeat.getId());

        return convertToResponseDto(savedEventSeat);
    }

    /**
     * Get event seat by ID
     */
    public EventSeatResponseDto getEventSeatById(Long id) {

        EventSeat eventSeat = eventSeatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event seat not found with id: " + id));

        return convertToResponseDto(eventSeat);
    }

    /**
     * Get all event seats
     */
    public List<EventSeatResponseDto> getAllEventSeats() {

        return eventSeatRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get all event seats for a specific event
     */
    public List<EventSeatResponseDto> getEventSeatsByEventId(Long eventId) {

        List<EventSeat> eventSeats = eventSeatRepository.findByEventId(eventId);

        return eventSeats.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get available event seats for a specific event
     */
    public List<EventSeatResponseDto> getAvailableEventSeatsByEventId(Long eventId) {

        List<EventSeat> eventSeats = eventSeatRepository.findByEventIdAndAvailability(eventId, true);

        return eventSeats.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Update event seat
     */
    @Transactional
    public EventSeatResponseDto updateEventSeat(Long id, EventSeatCreateDto updateDto) {

        EventSeat eventSeat = eventSeatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event seat not found with id: " + id));

        // Update fields
        if (updateDto.getSeatId() != null && !updateDto.getSeatId().equals(eventSeat.getSeat().getId())) {
            Seat seat = seatRepository.findById(updateDto.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found with id: " + updateDto.getSeatId()));
            eventSeat.setSeat(seat);
        }

        if (updateDto.getEventId() != null && !updateDto.getEventId().equals(eventSeat.getEvent().getId())) {
            Event event = eventRepository.findById(updateDto.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + updateDto.getEventId()));
            eventSeat.setEvent(event);
        }

        if (updateDto.getAvailability() != null) {
            eventSeat.setAvailability(updateDto.getAvailability());
        }

        if (updateDto.getPrice() != null) {
            eventSeat.setPrice(updateDto.getPrice());
        }

        EventSeat updatedEventSeat = eventSeatRepository.save(eventSeat);

        return convertToResponseDto(updatedEventSeat);
    }

    /**
     * Update event seat availability
     */
    @Transactional
    public EventSeatResponseDto updateEventSeatAvailability(Long id, Boolean availability) {

        EventSeat eventSeat = eventSeatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event seat not found with id: " + id));

        eventSeat.setAvailability(availability);
        EventSeat updatedEventSeat = eventSeatRepository.save(eventSeat);

        return convertToResponseDto(updatedEventSeat);
    }

    /**
     * Delete event seat
     */
    @Transactional
    public void deleteEventSeat(Long id) {

        if (!eventSeatRepository.existsById(id)) {
            throw new RuntimeException("Event seat not found with id: " + id);
        }

        eventSeatRepository.deleteById(id);
        log.info("Event seat deleted with ID: {}", id);
    }

    /**
     * Convert EventSeat entity to EventSeatResponseDto
     */
    private EventSeatResponseDto convertToResponseDto(EventSeat eventSeat) {
        return EventSeatResponseDto.builder()
                .id(eventSeat.getId())
                .seatId(eventSeat.getSeat().getId())
                .seatNumber(eventSeat.getSeat().getSeatNo())
                .row(eventSeat.getSeat().getRow())
                .seatCategory(eventSeat.getSeat().getCategory())
                .eventId(eventSeat.getEvent().getId())
                .eventName(eventSeat.getEvent().getName())
                .stadiumId(eventSeat.getEvent().getStadium() != null ? eventSeat.getEvent().getStadium().getId() : null)
                .availability(eventSeat.getAvailability())
                .bookingId(eventSeat.getBooking() != null ? eventSeat.getBooking().getId() : null)
                .price(eventSeat.getPrice())
                .build();
    }
}
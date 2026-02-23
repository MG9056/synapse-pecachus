package com.example.control.synapse.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.EventSeatCreateDto;
import com.example.control.synapse.dto.response.EventSeatResponseDto;
import com.example.control.synapse.service.interfaces.IEventSeatService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event-seats")
@RequiredArgsConstructor
public class EventSeatController {

    private final IEventSeatService eventSeatService;

    /**
     * Create event seats for all seats in stadium when event is created
     * POST /api/event-seats/bulk/event/{eventId}
     * Body: { "VIP": 100.0, "Regular": 50.0, "Economy": 25.0 }
     */
    @PostMapping("/bulk/event/{eventId}")
    public ResponseEntity<List<EventSeatResponseDto>> createEventSeatsForEvent(
            @PathVariable Long eventId,
            @RequestBody Map<String, Double> categoryPriceMap) {

        List<EventSeatResponseDto> eventSeats = eventSeatService.createEventSeatsForEvent(eventId, categoryPriceMap);
        return new ResponseEntity<>(eventSeats, HttpStatus.CREATED);
    }

    /**
     * Create a single event seat
     * POST /api/event-seats
     */
    @PostMapping
    public ResponseEntity<EventSeatResponseDto> createEventSeat(@Valid @RequestBody EventSeatCreateDto createDto) {
        EventSeatResponseDto eventSeat = eventSeatService.createEventSeat(createDto);
        return new ResponseEntity<>(eventSeat, HttpStatus.CREATED);
    }

    /**
     * Get event seat by ID
     * GET /api/event-seats/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventSeatResponseDto> getEventSeatById(@PathVariable Long id) {
        EventSeatResponseDto eventSeat = eventSeatService.getEventSeatById(id);
        return ResponseEntity.ok(eventSeat);
    }

    /**
     * Get all event seats
     * GET /api/event-seats
     */
    @GetMapping
    public ResponseEntity<List<EventSeatResponseDto>> getAllEventSeats() {
        List<EventSeatResponseDto> eventSeats = eventSeatService.getAllEventSeats();
        return ResponseEntity.ok(eventSeats);
    }

    /**
     * Get all event seats for a specific event
     * GET /api/event-seats/event/{eventId}
     */
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventSeatResponseDto>> getEventSeatsByEventId(@PathVariable Long eventId) {
        List<EventSeatResponseDto> eventSeats = eventSeatService.getEventSeatsByEventId(eventId);
        return ResponseEntity.ok(eventSeats);
    }

    /**
     * Get available event seats for a specific event
     * GET /api/event-seats/event/{eventId}/available
     */
    @GetMapping("/event/{eventId}/available")
    public ResponseEntity<List<EventSeatResponseDto>> getAvailableEventSeatsByEventId(@PathVariable Long eventId) {
        List<EventSeatResponseDto> eventSeats = eventSeatService.getAvailableEventSeatsByEventId(eventId);
        return ResponseEntity.ok(eventSeats);
    }

    /**
     * Update event seat
     * PUT /api/event-seats/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventSeatResponseDto> updateEventSeat(
            @PathVariable Long id,
            @Valid @RequestBody EventSeatCreateDto updateDto) {

        EventSeatResponseDto eventSeat = eventSeatService.updateEventSeat(id, updateDto);
        return ResponseEntity.ok(eventSeat);
    }

    /**
     * Update event seat availability
     * PATCH /api/event-seats/{id}/availability
     */
    @PatchMapping("/{id}/availability")
    public ResponseEntity<EventSeatResponseDto> updateEventSeatAvailability(
            @PathVariable Long id,
            @RequestParam Boolean availability) {

        EventSeatResponseDto eventSeat = eventSeatService.updateEventSeatAvailability(id, availability);
        return ResponseEntity.ok(eventSeat);
    }

    /**
     * Delete event seat
     * DELETE /api/event-seats/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventSeat(@PathVariable Long id) {
        eventSeatService.deleteEventSeat(id);
        return ResponseEntity.noContent().build();
    }
}

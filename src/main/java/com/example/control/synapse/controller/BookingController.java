package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.BookingRequest;
import com.example.control.synapse.dto.response.BookingResponseDto;
import com.example.control.synapse.service.interfaces.IBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService bookingService;

    @GetMapping("/allBookings")
    public List<BookingResponseDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/reserve")
    public Map<String, String> reserveSeat(@RequestBody BookingRequest request) {
        return bookingService.reserveSeat(request.getSeatIdList());
    }

    @PostMapping("/confirm")
    public Map<String, String> confirmBooking(@RequestBody BookingRequest request) {
        return bookingService.confirmBooking(
                request.getSeatIdList(),
                request.getUserId(),
                request.getEventId(),
                request.getStadiumId());
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponseDto> getBookingByUserId(@PathVariable Long userId) {
        return bookingService.getBookingByUserId(userId);
    }

    @GetMapping("/{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/event/{eventId}")
    public List<BookingResponseDto> getBookingByEventId(@PathVariable Long eventId) {
        return bookingService.getBookingByEventId(eventId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<BookingResponseDto> getBookingByStadiumId(@PathVariable Long stadiumId) {
        return bookingService.getBookingByStadiumId(stadiumId);
    }

}

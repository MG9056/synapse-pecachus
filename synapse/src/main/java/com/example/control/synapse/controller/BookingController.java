package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.service.BookingService;
import com.example.control.synapse.dto.request.BookingRequest;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Reserve a seat for 5 minutes
    @PostMapping("/reserve")
    public String reserveSeat(@RequestBody BookingRequest request) {
        return bookingService.reserveSeat(request.getSeatId());
    }

    // Confirm a booking
    @PostMapping("/confirm")
    public String confirmBooking(@RequestBody BookingRequest request) {
        return bookingService.confirmBooking(
            request.getSeatId(), 
            request.getUserId(), 
            request.getEventId()
        );
    }
}

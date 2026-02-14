package com.example.control.synapse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.service.BookingService;
import com.example.control.synapse.dto.request.BookingRequest;
import com.example.control.synapse.dto.response.BookingResponseDto;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.repository.BookingRepository;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    

    
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository=bookingRepository;
    }

    // Reserve a seat for 5 minutes
    @PostMapping("/reserve")
    public String reserveSeat(@RequestBody BookingRequest request) {
        return bookingService.reserveSeat(request.getSeatIdList());
    }

    // Confirm a booking
    @PostMapping("/confirm")
    public String confirmBooking(@RequestBody BookingRequest request) {
        return bookingService.confirmBooking(
            request.getSeatIdList(), 
            request.getUserId(), 
            request.getEventId()
        );
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponseDto> getBookingByUserId(@PathVariable Long userId)
    {return bookingService.getBookingByUserId(userId);
        
    }

}

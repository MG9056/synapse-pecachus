package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.service.BookingService;
import com.example.control.synapse.dto.request.BookingRequest;
import com.example.control.synapse.dto.response.BookingResponseDto;



@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    

    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
        
    }

    // Reserve a seat for 5 minutes
    @PostMapping("/reserve")
    public Map<String,String> reserveSeat(@RequestBody BookingRequest request) {
        return bookingService.reserveSeat(request.getSeatIdList());
    }

    // Confirm a booking
    @PostMapping("/confirm")
    public  Map<String,String> confirmBooking(@RequestBody BookingRequest request) {
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

    @GetMapping("/event/{eventId}")
    public List<BookingResponseDto> getBookingByEventId(@PathVariable Long eventId)
    {return bookingService.getBookingByEventId(eventId);
        
    }
    
    


}

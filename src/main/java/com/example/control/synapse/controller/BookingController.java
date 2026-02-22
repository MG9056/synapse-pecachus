package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.service.BookingService;
import com.example.control.synapse.dto.request.BookingRequest;

import com.example.control.synapse.models.Booking;


@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    private final BookingService bookingService;

    

    
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        
    }

    @GetMapping("/allBookings")
    public List<Booking> getAllBookings()
    {
        return bookingRepository.findAll();
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
            request.getEventId(),
            request.getStadiumId()
           
        );
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingByUserId(@PathVariable Long userId)
    {return bookingRepository.findByUser_Id(userId);
        
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id)
    {return bookingRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with id " + id));
        
    }

    @GetMapping("/event/{eventId}")
    public List<Booking> getBookingByEventId(@PathVariable Long eventId)
    {return bookingRepository.findByEvent_Id(eventId);
        
    }

     @GetMapping("/stadium/{stadiumId}")
    public List<Booking> getBookingByStadiumId(@PathVariable Long stadiumId)
    {return bookingRepository.findByStadium_Id(stadiumId);
        
    }

    
    


}

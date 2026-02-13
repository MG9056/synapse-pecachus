package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.EventSeat;
import com.example.control.synapse.models.User;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.EventSeatRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.repository.EventRepository;

import java.util.List;


import java.util.Map;

public class BookingService {

    private final EventSeatRepository eventSeatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


       private final Map<Long, ScheduledFuture<?>> reservationTimers = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

     public BookingService(EventSeatRepository seatRepo, BookingRepository bookingRepo, UserRepository userRepo, EventRepository eventRepo) {
        this.eventSeatRepository = seatRepo;
        this.bookingRepository = bookingRepo;
        this.userRepository=userRepo;
        this.eventRepository=eventRepo;
    }

        public String reserveSeat(List<Long> seatIdlist) {

            int size=seatIdlist.size();

            for(int j=0; j<size; j++)
{Long seatId=seatIdlist.get(j);
        EventSeat eventSeat = eventSeatRepository.findById(seatIdlist.get(j))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (eventSeat.getAvailability()==false) {
            return "Seat is already reserved or booked!";
        }

        eventSeat.setAvailability(false);
        eventSeatRepository.save(eventSeat);

        ScheduledFuture<?> timer = scheduler.schedule(() -> releaseSeat(seatId), 5, TimeUnit.MINUTES);
        reservationTimers.put(seatId, timer);
    }

        return "Seats reserved for 5 minutes. Confirm booking to finalize!";
    }



     public String confirmBooking(List<Long> seatIdlist, Long userId, Long eventId) {

        int size= seatIdlist.size();

       for(int i=0; i<size; i++)
{
        EventSeat eventSeat = eventSeatRepository.findById(seatIdlist.get(i))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (eventSeat.getAvailability()) {
            return "Seat reservation expired or was never reserved!";
        }

        // timer cancel ak logic
        ScheduledFuture<?> timer = reservationTimers.remove(seatIdlist.get(i));
        if (timer != null) {
            timer.cancel(false); // cacnels release seat ka execution
        }
    }

      
       
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Event event= eventRepository.findById(eventId)
        .orElseThrow(() -> new RuntimeException("Event not found"));

        
        Booking booking = new Booking();
        booking.setEventId(event);
        booking.setUserId(user);
        booking.setBookingTime(LocalDateTime.now());
        bookingRepository.save(booking);

        // saving same booking IDs in all seats

        for(int i=0; i<size; i++)
       { EventSeat bookedSeat= eventSeatRepository.findById(seatIdlist.get(i))
        .orElseThrow(() -> new RuntimeException("Seat not found"));
        
        bookedSeat.setBookingId(booking);
        eventSeatRepository.save(bookedSeat);
       }

        return "Booking confirmed!";
    }

     private void releaseSeat(Long seatId) {
        EventSeat eventSeat = eventSeatRepository.findById(seatId).orElse(null);
        if (eventSeat != null && !eventSeat.getAvailability()) {
            eventSeat.setAvailability(true);
            eventSeatRepository.save(eventSeat);
            reservationTimers.remove(seatId);
            System.out.println("Seat " + seatId + " released after 5 minutes");
        }
    }

      public void shutdownScheduler() {
        scheduler.shutdown();
    }
    
}

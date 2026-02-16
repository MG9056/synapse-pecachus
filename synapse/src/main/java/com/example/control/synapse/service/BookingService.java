package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.example.control.synapse.dto.response.BookingResponseDto;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
import com.example.control.synapse.dto.websocket.SeatUpdateMessage;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.EventSeat;
import com.example.control.synapse.models.User;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.EventSeatRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.repository.EventRepository;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

public class BookingService {

    private final EventSeatRepository eventSeatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
     private final SimpMessagingTemplate messagingTemplate;


       private final Map<Long, ScheduledFuture<?>> reservationTimers = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

     public BookingService(EventSeatRepository seatRepo, BookingRepository bookingRepo, UserRepository userRepo, EventRepository eventRepo, SimpMessagingTemplate messagingTemplate) {
        this.eventSeatRepository = seatRepo;
        this.bookingRepository = bookingRepo;
        this.userRepository=userRepo;
        this.eventRepository=eventRepo;
        this.messagingTemplate= messagingTemplate;
    }

    //Method for websocket, broadcastSeatUpdate is called in all 3->reserve,confirm,release and a msg of the class messageTemplate whatever is seat at the provided API

 private void broadcastSeatUpdate(EventSeat seat, String status) {

        SeatUpdateMessage msg = new SeatUpdateMessage(
            seat.getId(),                         // eventSeatId
            seat.getEventId().getId(),            // eventId
            // seat.getSeatId().getId(),             // physical seatId
            seat.getAvailability(),               // availability
            status,
            seat.getPrice()
    );

        messagingTemplate.convertAndSend(
                "/topic/event/" + seat.getEventId().getId(),
                msg
        );
    }



        public Map<String,String> reserveSeat(List<Long> seatIdlist) {

            int size=seatIdlist.size();

            for(int j=0; j<size; j++)
{Long seatId=seatIdlist.get(j);
        EventSeat eventSeat = eventSeatRepository.findById(seatIdlist.get(j))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (eventSeat.getAvailability()==false) {
              Map<String,String> response = new HashMap<>();
        response.put("message", "Seat is already booked or reserved!");
        return response;
        }

        eventSeat.setAvailability(false);
        eventSeatRepository.save(eventSeat);

         broadcastSeatUpdate(eventSeat, "RESERVED");

        ScheduledFuture<?> timer = scheduler.schedule(() -> releaseSeat(seatId), 5, TimeUnit.MINUTES);
        reservationTimers.put(seatId, timer);
    }

      Map<String,String> response = new HashMap<>();
        response.put("message", "Seats reserved for 5 minutes. Confirm booking to finalize!");
        return response;

        
    }



     public Map<String,String> confirmBooking(List<Long> seatIdlist, Long userId, Long eventId) {

        int size= seatIdlist.size();

       for(int i=0; i<size; i++)
{
        EventSeat eventSeat = eventSeatRepository.findById(seatIdlist.get(i))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (eventSeat.getAvailability()) {
              Map<String,String> response = new HashMap<>();
        response.put("message", "Seat reservation expired or not found!");
        return response;
        }

        // timer cancel ka logic
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

         broadcastSeatUpdate(bookedSeat, "BOOKED");
       }

          Map<String,String> response = new HashMap<>();
        response.put("message", "Booking confirmed!");
        return response;
    }

     private Map<String,String> releaseSeat(Long seatId) {
        EventSeat eventSeat = eventSeatRepository.findById(seatId).orElse(null);
        if (eventSeat != null && !eventSeat.getAvailability()) {
            eventSeat.setAvailability(true);
            eventSeatRepository.save(eventSeat);
            reservationTimers.remove(seatId);

             broadcastSeatUpdate(eventSeat, "AVAILABLE");
            
             Map<String,String> response = new HashMap<>();
        response.put("message", "Seat released after 5 minutes of waiting");
        return response;
        }

        else {
              Map<String,String> response = new HashMap<>();
        response.put("message", "Seat need not be released");
        return response;

            
        }
    }

      public void shutdownScheduler() {
        scheduler.shutdown();
    }

    public List<BookingResponseDto> getBookingByUserId(Long userId)
    {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        List<BookingResponseDto> dtoList= new ArrayList<>();


        for(Booking booking: bookings)
        {
            BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setUserId(booking.getUserId());
            bookingResponseDto.setEventId(booking.getEventId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());




        dtoList.add(bookingResponseDto);
        }



        return dtoList;


    }

    public List<BookingResponseDto> getBookingByEventId(Long eventId)
    {
        List<Booking> bookings = bookingRepository.findByEventId(eventId);
        List<BookingResponseDto> dtoList= new ArrayList<>();


        for(Booking booking: bookings)
        {
            BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setUserId(booking.getUserId());
            bookingResponseDto.setEventId(booking.getEventId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());




        dtoList.add(bookingResponseDto);
        }



        return dtoList;


    }


    
}

package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.example.control.synapse.dto.response.BookingResponseDto;

import com.example.control.synapse.dto.websocket.SeatUpdateMessage;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.EventSeat;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.EventSeatRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;

import jakarta.annotation.PreDestroy;

import com.example.control.synapse.repository.EventRepository;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

@Service
public class BookingService {

    private final EventSeatRepository eventSeatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final StadiumRepository stadiumRepository;


       private final Map<Long, ScheduledFuture<?>> reservationTimers = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

     public BookingService(EventSeatRepository seatRepo, BookingRepository bookingRepo, UserRepository userRepo, EventRepository eventRepo, SimpMessagingTemplate messagingTemplate, StadiumRepository stadiumRepository) {
        this.eventSeatRepository = seatRepo;
        this.bookingRepository = bookingRepo;
        this.userRepository=userRepo;
        this.eventRepository=eventRepo;
        this.messagingTemplate= messagingTemplate;
        this.stadiumRepository= stadiumRepository;
    }

    //Method for websocket, broadcastSeatUpdate is called in all 3->reserve,confirm,release and a msg of the class messageTemplate whatever is seat at the provided API

 private void broadcastSeatUpdate(EventSeat seat, String status) {

        SeatUpdateMessage msg = new SeatUpdateMessage(
            seat.getId(),                         // eventSeatId
            seat.getEvent().getId(),            // eventId
            // seat.getSeatId().getId(),             // physical seatId
            seat.getAvailability(),               // availability
            status,
            seat.getPrice()
    );

        messagingTemplate.convertAndSend(
                "/topic/event/" + seat.getEvent().getId(),
                msg
        );
    }



        public Map<String,String> reserveSeat(List<Long> seatIdlist) {

          
    for(Long seatId : seatIdlist) {
        EventSeat eventSeat = eventSeatRepository.findById(seatId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found with id " + seatId));

        if (eventSeat.getAvailability() == false) {
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



     public Map<String,String> confirmBooking(List<Long> seatIdlist, Long userId, Long eventId, Long stadiumId) {

       for(Long seatId : seatIdlist) {
        EventSeat eventSeat = eventSeatRepository.findById(seatId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found with id " + seatId));

        if (eventSeat.getAvailability()) {
            Map<String,String> response = new HashMap<>();
            response.put("message", "Seat reservation expired or not found!");
            return response;
        }

        // timer cancel ka logic
        ScheduledFuture<?> timer = reservationTimers.remove(seatId);
        if (timer != null) {
            timer.cancel(false); // cancels releaseSeat ka execution
        }
    }

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));

    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id " + eventId));

    Stadium stadium = stadiumRepository.findById(stadiumId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stadium not found with id " + stadiumId));

    Booking booking = new Booking();
    booking.setEvent(event);
    booking.setUser(user);
    booking.setStadium(stadium);
    booking.setBookingTime(LocalDateTime.now());
    bookingRepository.save(booking);

    // saving same booking IDs in all seats
    for(Long seatId : seatIdlist) {
        EventSeat bookedSeat = eventSeatRepository.findById(seatId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found with id " + seatId));

        bookedSeat.setBooking(booking);
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
    @PreDestroy
      private void shutdownScheduler() {
        scheduler.shutdown();
    }
    public BookingResponseDto getBookingById(Long id)
    {Booking booking = bookingRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with id " + id));
    
        BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setId(booking.getId());
            bookingResponseDto.setUserId(booking.getUser().getId());
            bookingResponseDto.setEventId(booking.getEvent().getId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());

            return bookingResponseDto;

        

    }

    public List<BookingResponseDto> getBookingByUserId(Long userId)
    {
        List<Booking> bookings = bookingRepository.findByUser_Id(userId);
        List<BookingResponseDto> dtoList= new ArrayList<>();


        for(Booking booking: bookings)
        {
            BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setId(booking.getId());
            bookingResponseDto.setUserId(booking.getUser().getId());
            bookingResponseDto.setEventId(booking.getEvent().getId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());




        dtoList.add(bookingResponseDto);
        }



        return dtoList;


    }

    public List<BookingResponseDto> getAllBookings()
    {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDto> dtoList= new ArrayList<>();


        for(Booking booking: bookings)
        {
            BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setId(booking.getId());
            bookingResponseDto.setUserId(booking.getUser().getId());
            bookingResponseDto.setEventId(booking.getEvent().getId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());




        dtoList.add(bookingResponseDto);
        }



        return dtoList;


    }

    public List<BookingResponseDto> getBookingByEventId(Long eventId)
    {
        List<Booking> bookings = bookingRepository.findByEvent_Id(eventId);
        List<BookingResponseDto> dtoList= new ArrayList<>();


        for(Booking booking: bookings)
        {
            BookingResponseDto bookingResponseDto= new BookingResponseDto();
            bookingResponseDto.setId(booking.getId());
            bookingResponseDto.setUserId(booking.getUser().getId());
            bookingResponseDto.setEventId(booking.getEvent().getId());
            bookingResponseDto.setBookingTime(booking.getBookingTime());




        dtoList.add(bookingResponseDto);
        }



        return dtoList;


    }


    
}

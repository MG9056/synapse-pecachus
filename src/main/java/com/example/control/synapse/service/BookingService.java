package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.response.BookingResponseDto;
import com.example.control.synapse.dto.response.EventSeatResponseDto;
import com.example.control.synapse.dto.websocket.SeatUpdateMessage;
import com.example.control.synapse.mapper.EventMapper;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.EventSeat;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.EventSeatRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.service.interfaces.IBookingService;

import jakarta.annotation.PreDestroy;

@Service
public class BookingService implements IBookingService {

    private final EventSeatRepository eventSeatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final StadiumRepository stadiumRepository;

    private final Map<Long, ScheduledFuture<?>> reservationTimers = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    public BookingService(EventSeatRepository seatRepo, BookingRepository bookingRepo, UserRepository userRepo,
            EventRepository eventRepo, SimpMessagingTemplate messagingTemplate, StadiumRepository stadiumRepository) {
        this.eventSeatRepository = seatRepo;
        this.bookingRepository = bookingRepo;
        this.userRepository = userRepo;
        this.eventRepository = eventRepo;
        this.messagingTemplate = messagingTemplate;
        this.stadiumRepository = stadiumRepository;
    }

    private BookingResponseDto convertToDto(Booking booking) {
        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setEventId(booking.getEvent().getId());
        dto.setStadiumId(booking.getStadium() != null ? booking.getStadium().getId() : null);
        dto.setBookingTime(booking.getBookingTime());

        // Populate Event info using mapper
        dto.setEvent(EventMapper.toDto(booking.getEvent()));

        // Fetch and populate seats
        List<EventSeatResponseDto> seats = eventSeatRepository.findByBookingId(booking.getId()).stream()
                .map(es -> EventSeatResponseDto.builder()
                        .id(es.getId())
                        .seatId(es.getSeat().getId())
                        .seatNumber(es.getSeat().getSeatNo())
                        .row(es.getSeat().getRow())
                        .seatCategory(es.getSeat().getCategory())
                        .eventId(es.getEvent().getId())
                        .eventName(es.getEvent().getName())
                        .stadiumId(es.getEvent().getStadium() != null ? es.getEvent().getStadium().getId() : null)
                        .availability(es.getAvailability())
                        .bookingId(booking.getId())
                        .price(es.getPrice())
                        .build())
                .collect(Collectors.toList());
        dto.setSeats(seats);

        return dto;
    }

    private void broadcastSeatUpdate(EventSeat seat, String status) {

        SeatUpdateMessage msg = new SeatUpdateMessage(
                seat.getId(), // eventSeatId
                seat.getEvent().getId(), // eventId
                seat.getAvailability(), // availability
                status,
                seat.getPrice());

        messagingTemplate.convertAndSend(
                "/topic/event/" + seat.getEvent().getId(),
                msg);
    }

    @Transactional
    public Map<String, String> reserveSeat(List<Long> seatIdlist) {
        for (Long seatId : seatIdlist) {
            EventSeat eventSeat = eventSeatRepository.findById(seatId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Seat not found with id " + seatId));

            if (eventSeat.getAvailability() == false) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Seat " + seatId + " is already booked or reserved!");
                return response;
            }

            eventSeat.setAvailability(false);
            eventSeatRepository.save(eventSeat);

            broadcastSeatUpdate(eventSeat, "RESERVED");

            ScheduledFuture<?> timer = scheduler.schedule(() -> releaseSeat(seatId), 5, TimeUnit.MINUTES);
            reservationTimers.put(seatId, timer);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Seats reserved for 5 minutes. Confirm booking to finalize!");
        return response;
    }

    @Transactional
    public Map<String, String> confirmBooking(List<Long> seatIdlist, Long userId, Long eventId, Long stadiumId) {
        if (seatIdlist == null || seatIdlist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat list cannot be empty");
        }

        List<EventSeat> eventSeatsToBook = new ArrayList<>();

        for (Long seatId : seatIdlist) {
            EventSeat eventSeat = eventSeatRepository.findById(seatId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Seat not found with id " + seatId));

            if (!eventSeat.getEvent().getId().equals(eventId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Seat " + seatId + " does not belong to event " + eventId);
            }
            if (!eventSeat.getEvent().getStadium().getId().equals(stadiumId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Seat " + seatId + " does not belong to stadium " + stadiumId);
            }

            if (eventSeat.getBooking() != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Seat " + seatId + " is already booked!");
                return response;
            }

            if (eventSeat.getAvailability()) {
                eventSeat.setAvailability(false);
            }

            ScheduledFuture<?> timer = reservationTimers.remove(seatId);
            if (timer != null) {
                timer.cancel(false);
            }

            eventSeatsToBook.add(eventSeat);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id " + eventId));

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(user);
        booking.setStadium(stadium);
        booking.setBookingTime(LocalDateTime.now());
        booking = bookingRepository.save(booking);

        for (EventSeat bookedSeat : eventSeatsToBook) {
            bookedSeat.setBooking(booking);
            bookedSeat.setAvailability(false);
            eventSeatRepository.save(bookedSeat);
            broadcastSeatUpdate(bookedSeat, "BOOKED");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Booking confirmed!");
        response.put("bookingId", booking.getId().toString());
        return response;
    }

    private Map<String, String> releaseSeat(Long seatId) {
        EventSeat eventSeat = eventSeatRepository.findById(seatId).orElse(null);
        if (eventSeat != null && !eventSeat.getAvailability()) {
            eventSeat.setAvailability(true);
            eventSeatRepository.save(eventSeat);
            reservationTimers.remove(seatId);

            broadcastSeatUpdate(eventSeat, "AVAILABLE");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Seat released after 5 minutes of waiting");
            return response;
        }

        else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Seat need not be released");
            return response;

        }
    }

    @PreDestroy
    private void shutdownScheduler() {
        scheduler.shutdown();
    }

    public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with id " + id));
        return convertToDto(booking);
    }

    public List<BookingResponseDto> getBookingByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDto> getBookingByEventId(Long eventId) {
        return bookingRepository.findByEvent_Id(eventId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDto> getBookingByStadiumId(Long stadiumId) {
        return bookingRepository.findByStadium_Id(stadiumId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

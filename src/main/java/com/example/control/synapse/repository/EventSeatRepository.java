package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventSeat;

public interface EventSeatRepository extends JpaRepository<EventSeat,Long> {
    List<EventSeat> findByEventId(Long eventId);
    
    List<EventSeat> findByEventIdAndAvailability(Long eventId, Boolean availability);
    
    List<EventSeat> findBySeatId(Long seatId);
    
    List<EventSeat> findByBookingId(Long bookingId);
    
    boolean existsByEventIdAndSeatId(Long eventId, Long seatId);
}

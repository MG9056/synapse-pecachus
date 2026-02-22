package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking>findByUser_Id(Long userId);
    List<Booking>findByEvent_Id(Long eventId);
    List<Booking>findByStadium_Id(Long eventId);

}

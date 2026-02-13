package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}

package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}

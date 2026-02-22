package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByStadiumId(Long stadiumId);
    Boolean existsByRowAndSeatNoAndStadiumId(String row, Integer seatNo, Long stadiumId);
    void deleteByStadiumId(Long stadiumId);
}


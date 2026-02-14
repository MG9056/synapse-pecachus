package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByStadiumId_Id(Long stadiumId);
    Boolean existsByRowAndSeatNoAndStadiumId_Id(String row, Integer seatNo, Long stadiumId);
    void deleteByStadiumId_Id(Long stadiumId);
}


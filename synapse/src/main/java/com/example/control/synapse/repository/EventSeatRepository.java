package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventSeat;

public interface EventSeatRepository extends JpaRepository<EventSeat,Long> {

}

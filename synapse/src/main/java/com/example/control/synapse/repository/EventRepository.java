package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Event;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    Optional<Event>findById(Long eventId);


}

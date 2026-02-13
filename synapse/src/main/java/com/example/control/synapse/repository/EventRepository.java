package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Event;

public interface EventRepository extends JpaRepository<Event,Long> {

}

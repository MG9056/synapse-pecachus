package com.example.control.synapse.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.control.synapse.models.Sos;

public interface SosRepository extends JpaRepository<Sos,Long> {

    List<Sos> findByUserId(Long userId);
    List<Sos> findByEventId(Long eventId);
    
}


package com.example.control.synapse.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.control.synapse.models.Sos;

public interface SosRepository extends JpaRepository<Sos,Long> {

    List<Sos> findByUser_Id(Long userId);
    List<Sos> findByEvent_Id(Long eventId);
    List<Sos> findByStadium_Id(Long stadiumId);
    
}


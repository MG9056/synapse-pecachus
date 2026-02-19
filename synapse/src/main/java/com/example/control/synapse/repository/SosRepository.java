package com.example.control.synapse.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.example.control.synapse.models.Sos;

public interface SosRepository extends JpaRepository<Sos,Long> {
    
}


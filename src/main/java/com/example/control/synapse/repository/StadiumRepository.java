package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Stadium;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    Optional<Stadium> findByAdminEmail(String adminEmail);
}
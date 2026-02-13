package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Merchandise;

public interface MerchandiseRepository extends JpaRepository<Merchandise,Long>{
    List<Merchandise> findAllByStadiumId(Long stadiumId);
}

package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventMerchandise;


public interface EventMerchandiseRepository extends JpaRepository<EventMerchandise,Long> {
    List<EventMerchandise> findByStadiumId(Long restaurantId);
    List<EventMerchandise> findByMerchandiseOrderId(Long orderId);
  


}

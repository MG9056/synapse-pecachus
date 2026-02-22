package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventMerchandise;


public interface EventMerchandiseRepository extends JpaRepository<EventMerchandise,Long> {
    List<EventMerchandise> findByStadium_Id(Long restaurantId);
    List<EventMerchandise> findByMerchandiseOrder_Id(Long orderId);
    List<EventMerchandise> findByEvent_Id(Long eventId);

  


}

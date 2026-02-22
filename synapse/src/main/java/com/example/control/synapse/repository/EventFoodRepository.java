package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventFood;


public interface EventFoodRepository extends JpaRepository<EventFood,Long> {
    List<EventFood> findByRestaurant_Id(Long restaurantId);
    List<EventFood> findByOrder_Id(Long orderId);
     List<EventFood> findByEvent_Id(Long eventId);
  


}

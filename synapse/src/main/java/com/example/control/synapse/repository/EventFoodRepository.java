package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.EventFood;
import com.example.control.synapse.models.Food;

public interface EventFoodRepository extends JpaRepository<EventFood,Long> {
    List<EventFood> findByRestaurantId(Long restaurantId);
    List<EventFood> findByOrderId(Long orderId);
  


}

package com.example.control.synapse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Food;

public interface FoodRepository extends JpaRepository<Food,Long>{
    
    List<Food> findByRestaurantId(Long restaurantId);
}

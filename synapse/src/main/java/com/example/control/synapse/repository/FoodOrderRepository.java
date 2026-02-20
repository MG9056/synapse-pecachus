package com.example.control.synapse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.FoodOrder;





    
public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long>
{

    List<FoodOrder>findByUserId(Long userId);
    List<FoodOrder>findByRestaurantId(Long restaurantId);
    
}

package com.example.control.synapse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.models.Restaurant;





    
public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long>
{

    List<FoodOrder>findByUserId(Long userId);
    List<FoodOrder>findByRestaurantId(Long restaurantId);
    
}

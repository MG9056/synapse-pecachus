package com.example.control.synapse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Order;
import com.example.control.synapse.models.Restaurant;





    
public interface OrderRepository extends JpaRepository<Order,Long>
{

    List<Order>findByUserId(Long userId);
    
}

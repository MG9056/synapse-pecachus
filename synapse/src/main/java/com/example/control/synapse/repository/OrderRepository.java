package com.example.control.synapse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Order;





    
public interface OrderRepository extends JpaRepository<Order,Long>
{
    
}

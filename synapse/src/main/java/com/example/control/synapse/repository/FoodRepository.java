package com.example.control.synapse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Food;

public interface FoodRepository extends JpaRepository<Food,Long>{
    
}

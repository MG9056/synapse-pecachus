package com.example.control.synapse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.control.synapse.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{

List<Restaurant> findByIdId(Long id);
List<Restaurant>findByStadiumId(Long stadiumId);

}

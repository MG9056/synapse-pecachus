package com.example.control.synapse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.MerchandiseOrder;

public interface MerchandiseOrderRepository extends JpaRepository<MerchandiseOrder,Long>
{

    List<MerchandiseOrder>findByUser_Id(Long userId);
    List<MerchandiseOrder>findByStadium_Id(Long stadiumId);
    
}

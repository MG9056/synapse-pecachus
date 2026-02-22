package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.MerchandiseOrderRequest;


import com.example.control.synapse.models.MerchandiseOrder;
import com.example.control.synapse.repository.MerchandiseOrderRepository;
import com.example.control.synapse.service.MerchandiseOrderService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/merchandiseOrder")
public class MerchandiseOrderController {

    private final MerchandiseOrderRepository merchandiseOrderRepository;

    private final MerchandiseOrderService merchandiseOrderService;
 

    public MerchandiseOrderController(MerchandiseOrderService merchandiseOrderSerive, MerchandiseOrderRepository merchandiseOrderRepository)
    {
        this.merchandiseOrderService=merchandiseOrderSerive;
        this.merchandiseOrderRepository = merchandiseOrderRepository;
        
    }

    @GetMapping("/allMerchandiseOrders")
      public List<MerchandiseOrder> getAllMerchandiseOrder()
    { return merchandiseOrderRepository.findAll();
        
    }

    @PostMapping("/placeMerchandiseOrder")
    public  Map<String,String> bookFoodOrder(@RequestBody MerchandiseOrderRequest request) {
    

        return merchandiseOrderService.bookMerchandiseOrder(
            request.getMerchandiseIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId(),
            request.getStadiumId(),
            request.getEventId(),
            request.getOrderTime()

        );
    }

   

    @GetMapping("/user/{userId}")
    public List<MerchandiseOrder> getMerchandiseOrderByUserId(@PathVariable Long userId)
    { return merchandiseOrderRepository.findByUser_Id(userId);
        
    }

    @GetMapping("/{id}")
    public MerchandiseOrder getMerchandiseOrderById(@PathVariable Long id)
    {
        return merchandiseOrderRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MerchandiseOrder not found with id " + id));
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<MerchandiseOrder> getMerchandiseOrderByStadiumId(@PathVariable Long stadiumId)
    {
        return merchandiseOrderRepository.findByStadium_Id(stadiumId);
    }

   
    







    
}

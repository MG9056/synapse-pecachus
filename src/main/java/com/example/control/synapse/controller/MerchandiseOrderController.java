package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.control.synapse.dto.request.MerchandiseOrderRequest;

import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;

import com.example.control.synapse.service.MerchandiseOrderService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/merchandiseOrder")
public class MerchandiseOrderController {

    private final MerchandiseOrderService merchandiseOrderService;
 

    public MerchandiseOrderController(MerchandiseOrderService merchandiseOrderSerive)
    {
        this.merchandiseOrderService=merchandiseOrderSerive;
        
    }

    @GetMapping("/allMerchandiseOrders")
      public List<MerchandiseOrderResponseDto> getAllMerchandiseOrder()
    { return merchandiseOrderService.getAllMerchandiseOrders();
        
    }

    @PostMapping("/placeMerchandiseOrder")
    public  Map<String,String> bookFoodOrder(@RequestBody MerchandiseOrderRequest request) {
    

        return merchandiseOrderService.bookMerchandiseOrder(
            request.getMerchandiseIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId(),
            request.getStadiumId()
        );
    }

   

    @GetMapping("/user/{userId}")
    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(@PathVariable Long id)
    { return merchandiseOrderService.getMerchandiseOrderByUserId(id);
        
    }

    @GetMapping("{id}")
    public MerchandiseOrderResponseDto getMerchandiseOrderById(@PathVariable Long id)
    {
        return merchandiseOrderService.getMerchandiseOrderById(id);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(@PathVariable Long stadiumid)
    {
        return merchandiseOrderService.getMerchandiseOrderByStadiumId(stadiumid);
    }

   
    







    
}

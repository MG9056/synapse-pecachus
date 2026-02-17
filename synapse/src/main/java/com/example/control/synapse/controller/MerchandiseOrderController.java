package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FoodOrderRequest;
import com.example.control.synapse.dto.request.MerchandiseOrderRequest;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.FoodOrderRepository;
import com.example.control.synapse.repository.MerchandiseOrderRepository;
import com.example.control.synapse.service.FoodOrderService;
import com.example.control.synapse.service.MerchandiseOrderService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/FoodOrder")
public class MerchandiseOrderController {

    private final MerchandiseOrderService merchandiseOrderService;
    private final MerchandiseOrderRepository merchandiseOrderRepository;

    public MerchandiseOrderController(MerchandiseOrderService merchandiseOrderSerive, MerchandiseOrderRepository merchandiseOrderRepository)
    {
        this.merchandiseOrderService=merchandiseOrderSerive;
        this.merchandiseOrderRepository=merchandiseOrderRepository;
    }

    @PostMapping("/placeMerchandiseOrder")
    public  Map<String,String> bookFoodOrder(@RequestBody MerchandiseOrderRequest request)
    {

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

    @GetMapping("/stadium/{stadiumId}")
    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(@PathVariable Long stadiumid)
    {
        return merchandiseOrderService.getMerchandiseOrderByStadiumId(stadiumid);
    }

   
    







    
}

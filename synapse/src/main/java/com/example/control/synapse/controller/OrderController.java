package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.OrderRequest;
import com.example.control.synapse.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderSerive)
    {
        this.orderService=orderSerive;
    }

    @PostMapping("/placeOrder")
    public String bookOrder(@RequestBody OrderRequest request)
    {

        return orderService.bookOrder(
            request.getFoodIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId()
        );
    }

   
    







    
}

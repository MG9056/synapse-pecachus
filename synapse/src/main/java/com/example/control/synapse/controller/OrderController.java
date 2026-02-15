package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.OrderRequest;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.dto.response.OrderResponseDto;
import com.example.control.synapse.models.Booking;
import com.example.control.synapse.models.Order;
import com.example.control.synapse.repository.BookingRepository;
import com.example.control.synapse.repository.OrderRepository;
import com.example.control.synapse.service.OrderService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Order")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderSerive, OrderRepository orderRepository)
    {
        this.orderService=orderSerive;
        this.orderRepository=orderRepository;
    }

    @PostMapping("/placeOrder")
<<<<<<< HEAD
    public Map<String,String> bookOrder(@RequestBody OrderRequest request)
=======
    public  Map<String,String> bookOrder(@RequestBody OrderRequest request)
>>>>>>> 571528aed8dfd8d61658a96b04c87bf62084202e
    {

        return orderService.bookOrder(
            request.getFoodIdlist(),
            request.getUserId(),
            request.getPrice(),
            request.getSeatId()
        );
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrderByRestaurantId(@PathVariable Long id)
    { return orderService.getOrderByRestaurantId(id);
        
    }

   
    







    
}

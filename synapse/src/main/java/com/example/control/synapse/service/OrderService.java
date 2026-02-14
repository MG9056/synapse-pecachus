package com.example.control.synapse.service;


import java.util.List;


import org.springframework.stereotype.Service;


import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.Order;
import com.example.control.synapse.models.EventFood;

import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.*;

@Service
public class OrderService {

    private final SeatRepository seatRepository;
    private final EventFoodRepository eventFoodRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    


    public OrderService(EventFoodRepository eventFoodRepository, OrderRepository orderRepository, UserRepository userRepository, FoodRepository foodRepository, SeatRepository seatRepository)
    {this.eventFoodRepository=eventFoodRepository;
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
        this.foodRepository= foodRepository;
        this.seatRepository = seatRepository;
        


    }

    public String bookOrder(List<Long>foodIdlist, Long userId,float price, Long seatId)
    {int size= foodIdlist.size();

         User user = (User) userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));
        

        Seat seat= seatRepository.findById(seatId)
        .orElseThrow(() -> new RuntimeException("Seat not found"));




        Order order = new Order();
        order.setPrice(price);
        order.setUserId(user);
        order.setSeatId(seat);
        orderRepository.save(order);
        

        for(int i=0; i<size; i++)
        {EventFood bookedFood= eventFoodRepository.findById(foodIdlist.get(i))
            .orElseThrow(() -> new RuntimeException("Food not found"));

        bookedFood.setOrderId(order);
        eventFoodRepository.save(bookedFood);
        










        }



        return "Order Booked!";


    }


    
}

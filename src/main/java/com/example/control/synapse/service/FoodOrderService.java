package com.example.control.synapse.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.EventFood;

import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.*;

@Service
public class FoodOrderService {

    private final SeatRepository seatRepository;
    private final EventFoodRepository eventFoodRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final EventRepository eventRepository;
    


    public FoodOrderService(EventFoodRepository eventFoodRepository, FoodOrderRepository foodOrderRepository, UserRepository userRepository, FoodRepository foodRepository, SeatRepository seatRepository, RestaurantRepository restaurantRepository, EventRepository eventRepository)
    {this.eventFoodRepository=eventFoodRepository;
        this.foodOrderRepository=foodOrderRepository;
        this.userRepository=userRepository;
        this.foodRepository= foodRepository;
        this.seatRepository = seatRepository;
        this.restaurantRepository= restaurantRepository;
        this.eventRepository=eventRepository;


    }

    public Map<String,String> bookFoodOrder(List<Long>foodIdlist, Long userId,float price, Long seatId, Long restaurantId, Long eventId, LocalDateTime orderTime)
    {

        User user = userRepository.findById(userId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));

      Seat seat = seatRepository.findById(seatId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found with id " + seatId));

       Restaurant restaurant = restaurantRepository.findById(restaurantId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id " + restaurantId));


       Event event = eventRepository.findById(eventId)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id " + eventId));
        



        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setPrice(price);
        foodOrder.setUser(user);
        foodOrder.setSeat(seat);
        foodOrder.setRestaurant(restaurant);
        foodOrder.setEvent(event);
        foodOrder.setOrderTime(orderTime);
        foodOrderRepository.save(foodOrder);
        

        for(Long foodId : foodIdlist)
{
    Food bookedFood = foodRepository.findById(foodId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found with id " + foodId));

    EventFood eventFood = new EventFood();
    eventFood.setName(bookedFood.getName());
    eventFood.setRestaurant(bookedFood.getRestaurant());
    eventFood.setPrice(bookedFood.getPrice());
    eventFood.setRating(bookedFood.getRating());
    eventFood.setEvent(event);
    eventFood.setOrder(foodOrder);
    eventFoodRepository.save(eventFood);
}

  Map<String,String> response = new HashMap<>();
        response.put("message", "Food Order booked!");
        return response;

        


    }




    public List<FoodOrderResponseDto> getFoodOrderByUserId(Long userId)
    {List<FoodOrder> orders= foodOrderRepository.findByUser_Id((userId));

        List<FoodOrderResponseDto> dtoList= new ArrayList<>();

        for(FoodOrder order:orders)
        {FoodOrderResponseDto orderResponseDto= new FoodOrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setSeatId(order.getSeat().getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setRestaurantId(order.getRestaurant().getId());

        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }

    public List<FoodOrderResponseDto> getAllFoodOrders()
    {List<FoodOrder> orders= foodOrderRepository.findAll();

        List<FoodOrderResponseDto> dtoList= new ArrayList<>();

        for(FoodOrder order:orders)
        {FoodOrderResponseDto orderResponseDto= new FoodOrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setSeatId(order.getSeat().getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setRestaurantId(order.getRestaurant().getId());

        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }

    public List<FoodOrderResponseDto> getFoodOrderByRestaurantId(Long restaurantId)
    {List<FoodOrder> orders= foodOrderRepository.findByRestaurant_Id((restaurantId));

        List<FoodOrderResponseDto> dtoList= new ArrayList<>();

        for(FoodOrder order:orders)
        {FoodOrderResponseDto orderResponseDto= new FoodOrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setSeatId(order.getSeat().getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setRestaurantId(order.getRestaurant().getId());

        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }

    public FoodOrderResponseDto getFoodOrderById(Long id)
    {FoodOrder order = foodOrderRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food order not found with id " + id));

        FoodOrderResponseDto orderResponseDto= new FoodOrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setSeatId(order.getSeat().getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setRestaurantId(order.getRestaurant().getId());

        return orderResponseDto;





    }




    
}

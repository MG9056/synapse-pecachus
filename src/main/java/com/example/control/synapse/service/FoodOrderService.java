package com.example.control.synapse.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.dto.response.FoodOrderResponseDto;
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
    


    public FoodOrderService(EventFoodRepository eventFoodRepository, FoodOrderRepository foodOrderRepository, UserRepository userRepository, FoodRepository foodRepository, SeatRepository seatRepository, RestaurantRepository restaurantRepository)
    {this.eventFoodRepository=eventFoodRepository;
        this.foodOrderRepository=foodOrderRepository;
        this.userRepository=userRepository;
        this.foodRepository= foodRepository;
        this.seatRepository = seatRepository;
        this.restaurantRepository= restaurantRepository;
        


    }

    public Map<String,String> bookFoodOrder(List<Long>foodIdlist, Long userId,float price, Long seatId, Long restaurantId)
    {int size= foodIdlist.size();

         User user = (User) userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));
        

        Seat seat= seatRepository.findById(seatId)
        .orElseThrow(() -> new RuntimeException("Seat not found"));

        Restaurant restaurant= restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RuntimeException("Restaurant not found"));




        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setPrice(price);
        foodOrder.setUser(user);
        foodOrder.setSeat(seat);
        foodOrder.setRestaurant(restaurant);
        foodOrderRepository.save(foodOrder);
        

        for(int i=0; i<size; i++)
        {Food bookedFood= foodRepository.findById(foodIdlist.get(i))
            .orElseThrow(() -> new RuntimeException("Food not found"));

            EventFood eventFood= new EventFood();

            eventFood.setName(bookedFood.getName());
            eventFood.setRestaurant(bookedFood.getRestaurant());
            eventFood.setPrice(bookedFood.getPrice());
            eventFood.setRating(bookedFood.getRating());

            eventFood.setOrder(foodOrder);
            eventFoodRepository.save(eventFood);
        










        }

  Map<String,String> response = new HashMap<>();
        response.put("message", "Food Order booked!");
        return response;

        


    }




    public List<FoodOrderResponseDto> getFoodOrderByUserId(Long userId)
    {List<FoodOrder> orders= foodOrderRepository.findByUserId((userId));

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
    {List<FoodOrder> orders= foodOrderRepository.findByRestaurantId((restaurantId));

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
    {FoodOrder order= foodOrderRepository.findById(id).orElseThrow();

        FoodOrderResponseDto orderResponseDto= new FoodOrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setSeatId(order.getSeat().getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setRestaurantId(order.getRestaurant().getId());

        return orderResponseDto;





    }




    
}

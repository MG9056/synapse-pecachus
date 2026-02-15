package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.EventFoodResponseDto;
import com.example.control.synapse.dto.response.FoodResponseDto;
import com.example.control.synapse.models.EventFood;
import com.example.control.synapse.models.Food;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.EventFoodRepository;

public class EventFoodService {

    private final EventFoodRepository eventFoodRepository;

    public EventFoodService(EventFoodRepository eventFoodRepository)
    {
        this.eventFoodRepository= eventFoodRepository;
    }


    public EventFoodResponseDto getEventFoodById(Long id)
    {EventFood eventFood= eventFoodRepository.findById(id).orElseThrow();
     
        EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();

        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurantId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrderId());

        return eventFoodResponseDto;







    }


    public List<EventFoodResponseDto> getEventFoodByOrderId(Long orderId)
 {List<EventFood> eventFoods= eventFoodRepository.findByOrderId(orderId);
    List<EventFoodResponseDto> dtoList= new ArrayList<>();

    for(EventFood eventFood:eventFoods)
    {

       EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();

        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurantId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrderId());

        dtoList.add(eventFoodResponseDto);

    }

    return dtoList;

   }


   public List<EventFoodResponseDto> getEventFoodByRestaurantId(Long restaurantId)
 {List<EventFood> eventFoods= eventFoodRepository.findByRestaurantId(restaurantId);
    List<EventFoodResponseDto> dtoList= new ArrayList<>();

    for(EventFood eventFood:eventFoods)
    {

       EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();

        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurantId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrderId());

        dtoList.add(eventFoodResponseDto);

    }

    return dtoList;

   }

    public List<EventFoodResponseDto> getAllEventFood()
 {List<EventFood> eventFoods= eventFoodRepository.findAll();
    List<EventFoodResponseDto> dtoList= new ArrayList<>();

    for(EventFood eventFood:eventFoods)
    {

       EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();

        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurantId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrderId());

        dtoList.add(eventFoodResponseDto);

    }

    return dtoList;

   }


   
    
}

package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.EventFoodResponseDto;

import com.example.control.synapse.models.EventFood;

import com.example.control.synapse.repository.EventFoodRepository;


@Service
public class EventFoodService {

    private final EventFoodRepository eventFoodRepository;

    public EventFoodService(EventFoodRepository eventFoodRepository)
    {
        this.eventFoodRepository= eventFoodRepository;
    }


    public EventFoodResponseDto getEventFoodById(Long id)
    {EventFood eventFood= eventFoodRepository.findById(id).orElseThrow();
     
        EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();

      eventFoodResponseDto.setId(eventFood.getId());
        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurant().getId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrder().getId());

        return eventFoodResponseDto;







    }


    public List<EventFoodResponseDto> getEventFoodByOrderId(Long orderId)
 {List<EventFood> eventFoods= eventFoodRepository.findByOrder_Id(orderId);
    List<EventFoodResponseDto> dtoList= new ArrayList<>();

    for(EventFood eventFood:eventFoods)
    {

       EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();
       
       eventFoodResponseDto.setId(eventFood.getId());
        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurant().getId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrder().getId());

        dtoList.add(eventFoodResponseDto);

    }

    return dtoList;

   }


   public List<EventFoodResponseDto> getEventFoodByRestaurantId(Long restaurantId)
 {List<EventFood> eventFoods= eventFoodRepository.findByRestaurant_Id(restaurantId);
    List<EventFoodResponseDto> dtoList= new ArrayList<>();

    for(EventFood eventFood:eventFoods)
    {

       EventFoodResponseDto eventFoodResponseDto= new EventFoodResponseDto();
         eventFoodResponseDto.setId(eventFood.getId());
        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurant().getId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrder().getId());

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
      eventFoodResponseDto.setId(eventFood.getId());
        eventFoodResponseDto.setName(eventFood.getName());
        eventFoodResponseDto.setRestaurantId(eventFood.getRestaurant().getId());
        eventFoodResponseDto.setPrice(eventFood.getPrice());
        eventFoodResponseDto.setRating(eventFood.getRating());
        eventFoodResponseDto.setOrderId(eventFood.getOrder().getId());

        dtoList.add(eventFoodResponseDto);

    }

    return dtoList;

   }


   
    
}

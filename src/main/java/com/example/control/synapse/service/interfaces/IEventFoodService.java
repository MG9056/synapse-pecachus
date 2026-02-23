package com.example.control.synapse.service.interfaces;

import java.util.List;

import com.example.control.synapse.dto.response.EventFoodResponseDto;

public interface IEventFoodService {
    EventFoodResponseDto getEventFoodById(Long id);

    List<EventFoodResponseDto> getEventFoodByOrderId(Long orderId);

    List<EventFoodResponseDto> getEventFoodByRestaurantId(Long restaurantId);

    List<EventFoodResponseDto> getAllEventFood();

    List<EventFoodResponseDto> getEventFoodByEventId(Long eventId);
}

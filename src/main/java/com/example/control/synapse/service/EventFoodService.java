package com.example.control.synapse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.response.EventFoodResponseDto;
import com.example.control.synapse.models.EventFood;
import com.example.control.synapse.repository.EventFoodRepository;
import com.example.control.synapse.service.interfaces.IEventFoodService;

@Service
public class EventFoodService implements IEventFoodService {

   private final EventFoodRepository eventFoodRepository;

   public EventFoodService(EventFoodRepository eventFoodRepository) {
      this.eventFoodRepository = eventFoodRepository;
   }

   private EventFoodResponseDto convertToDto(EventFood eventFood) {
      EventFoodResponseDto dto = new EventFoodResponseDto();
      dto.setId(eventFood.getId());
      dto.setName(eventFood.getName());
      dto.setRestaurantId(eventFood.getRestaurant() != null ? eventFood.getRestaurant().getId() : null);
      dto.setPrice(eventFood.getPrice());
      dto.setRating(eventFood.getRating());
      dto.setOrderId(eventFood.getOrder() != null ? eventFood.getOrder().getId() : null);
      dto.setEventId(eventFood.getEvent() != null ? eventFood.getEvent().getId() : null);
      return dto;
   }

   public EventFoodResponseDto getEventFoodById(Long id) {
      EventFood eventFood = eventFoodRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventFood not found with id " + id));
      return convertToDto(eventFood);
   }

   public List<EventFoodResponseDto> getEventFoodByOrderId(Long orderId) {
      return eventFoodRepository.findByOrder_Id(orderId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
   }

   public List<EventFoodResponseDto> getEventFoodByRestaurantId(Long restaurantId) {
      return eventFoodRepository.findByRestaurant_Id(restaurantId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
   }

   public List<EventFoodResponseDto> getAllEventFood() {
      return eventFoodRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
   }

   public List<EventFoodResponseDto> getEventFoodByEventId(Long eventId) {
      return eventFoodRepository.findByEvent_Id(eventId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
   }

}

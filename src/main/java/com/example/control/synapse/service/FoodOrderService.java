package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import com.example.control.synapse.service.interfaces.IFoodOrderService;

@Service
public class FoodOrderService implements IFoodOrderService {

        private final SeatRepository seatRepository;
        private final EventFoodRepository eventFoodRepository;
        private final FoodOrderRepository foodOrderRepository;
        private final UserRepository userRepository;
        private final FoodRepository foodRepository;
        private final RestaurantRepository restaurantRepository;
        private final EventRepository eventRepository;

        public FoodOrderService(EventFoodRepository eventFoodRepository, FoodOrderRepository foodOrderRepository,
                        UserRepository userRepository, FoodRepository foodRepository, SeatRepository seatRepository,
                        RestaurantRepository restaurantRepository, EventRepository eventRepository) {
                this.eventFoodRepository = eventFoodRepository;
                this.foodOrderRepository = foodOrderRepository;
                this.userRepository = userRepository;
                this.foodRepository = foodRepository;
                this.seatRepository = seatRepository;
                this.restaurantRepository = restaurantRepository;
                this.eventRepository = eventRepository;
        }

        private FoodOrderResponseDto convertToDto(FoodOrder order) {
                FoodOrderResponseDto dto = new FoodOrderResponseDto();
                dto.setId(order.getId());
                dto.setPrice(order.getPrice());
                dto.setSeatId(order.getSeat() != null ? order.getSeat().getId() : null);
                dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
                dto.setRestaurantId(order.getRestaurant() != null ? order.getRestaurant().getId() : null);
                dto.setEventId(order.getEvent() != null ? order.getEvent().getId() : null);
                dto.setOrderTime(order.getOrderTime());
                return dto;
        }

        @Transactional
        public Map<String, String> bookFoodOrder(List<Long> foodIdlist, Long userId, float price, Long seatId,
                        Long restaurantId, Long eventId, LocalDateTime orderTime) {

                User user = userRepository.findById(userId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "User not found with id " + userId));

                Seat seat = seatRepository.findById(seatId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Seat not found with id " + seatId));

                Restaurant restaurant = restaurantRepository.findById(restaurantId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Restaurant not found with id " + restaurantId));

                Event event = eventRepository.findById(eventId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Event not found with id " + eventId));

                FoodOrder foodOrder = new FoodOrder();
                foodOrder.setPrice(price);
                foodOrder.setUser(user);
                foodOrder.setSeat(seat);
                foodOrder.setRestaurant(restaurant);
                foodOrder.setEvent(event);
                foodOrder.setOrderTime(orderTime);
                foodOrderRepository.save(foodOrder);

                for (Long foodId : foodIdlist) {
                        Food bookedFood = foodRepository.findById(foodId)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Food not found with id " + foodId));

                        EventFood eventFood = new EventFood();
                        eventFood.setName(bookedFood.getName());
                        eventFood.setRestaurant(bookedFood.getRestaurant());
                        eventFood.setPrice(bookedFood.getPrice());
                        eventFood.setRating(bookedFood.getRating());
                        eventFood.setEvent(event);
                        eventFood.setOrder(foodOrder);
                        eventFoodRepository.save(eventFood);
                }

                Map<String, String> response = new HashMap<>();
                response.put("message", "Food Order booked!");
                return response;
        }

        public List<FoodOrderResponseDto> getFoodOrderByUserId(Long userId) {
                return foodOrderRepository.findByUser_Id(userId).stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public List<FoodOrderResponseDto> getAllFoodOrders() {
                return foodOrderRepository.findAll().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public List<FoodOrderResponseDto> getFoodOrderByRestaurantId(Long restaurantId) {
                return foodOrderRepository.findByRestaurant_Id(restaurantId).stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public FoodOrderResponseDto getFoodOrderById(Long id) {
                FoodOrder order = foodOrderRepository.findById(id)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Food order not found with id " + id));
                return convertToDto(order);
        }

        public List<FoodOrderResponseDto> getFoodOrderByEventId(Long eventId) {
                return foodOrderRepository.findByEvent_Id(eventId).stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

}

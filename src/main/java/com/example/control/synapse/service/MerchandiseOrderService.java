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
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.MerchandiseOrder;
import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.*;
import com.example.control.synapse.service.interfaces.IMerchandiseOrderService;

@Service
public class MerchandiseOrderService implements IMerchandiseOrderService {

        private final SeatRepository seatRepository;
        private final EventMerchandiseRepository eventMerchandiseRepository;
        private final MerchandiseOrderRepository merchandiseOrderRepository;
        private final UserRepository userRepository;
        private final MerchandiseRepository merchandiseRepository;
        private final StadiumRepository stadiumRepository;
        private final EventRepository eventRepository;

        public MerchandiseOrderService(EventMerchandiseRepository eventMerchandiseRepository,
                        MerchandiseOrderRepository merchandiseOrderRepository, UserRepository userRepository,
                        MerchandiseRepository merchandiseRepository, SeatRepository seatRepository,
                        StadiumRepository stadiumRepository, EventRepository eventRepository) {
                this.eventMerchandiseRepository = eventMerchandiseRepository;
                this.merchandiseOrderRepository = merchandiseOrderRepository;
                this.userRepository = userRepository;
                this.merchandiseRepository = merchandiseRepository;
                this.seatRepository = seatRepository;
                this.stadiumRepository = stadiumRepository;
                this.eventRepository = eventRepository;
        }

        private MerchandiseOrderResponseDto convertToDto(MerchandiseOrder order) {
                MerchandiseOrderResponseDto dto = new MerchandiseOrderResponseDto();
                dto.setId(order.getId());
                dto.setPrice(order.getPrice());
                dto.setSeatId(order.getSeat() != null ? order.getSeat().getId() : null);
                dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
                dto.setStadiumId(order.getStadium() != null ? order.getStadium().getId() : null);
                dto.setEventId(order.getEvent() != null ? order.getEvent().getId() : null);
                dto.setOrderTime(order.getOrderTime());
                return dto;
        }

        @Transactional
        public Map<String, String> bookMerchandiseOrder(List<Long> merchandiseIdlist, Long userId, float price,
                        Long seatId,
                        Long stadiumId, Long eventId, LocalDateTime orderTime) {

                User user = userRepository.findById(userId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "User not found with id " + userId));

                Seat seat = seatRepository.findById(seatId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Seat not found with id " + seatId));

                Stadium stadium = stadiumRepository.findById(stadiumId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Stadium not found with id " + stadiumId));

                Event event = eventRepository.findById(eventId)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Event not found with id " + eventId));

                MerchandiseOrder merchandiseOrder = new MerchandiseOrder();
                merchandiseOrder.setPrice(price);
                merchandiseOrder.setUser(user);
                merchandiseOrder.setSeat(seat);
                merchandiseOrder.setStadium(stadium);
                merchandiseOrder.setEvent(event);
                merchandiseOrder.setOrderTime(orderTime);
                merchandiseOrderRepository.save(merchandiseOrder);

                for (Long merchandiseId : merchandiseIdlist) {
                        Merchandise bookedMerchandise = merchandiseRepository.findById(merchandiseId)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Merchandise not found with id " + merchandiseId));

                        EventMerchandise eventMerchandise = new EventMerchandise();
                        eventMerchandise.setName(bookedMerchandise.getName());
                        eventMerchandise.setDescription(bookedMerchandise.getDescription());
                        eventMerchandise.setPrice(bookedMerchandise.getPrice());
                        eventMerchandise.setRating(bookedMerchandise.getRating());
                        eventMerchandise.setStadium(stadium);
                        eventMerchandise.setEvent(event);
                        eventMerchandise.setMerchandiseOrder(merchandiseOrder);
                        eventMerchandiseRepository.save(eventMerchandise);
                }

                Map<String, String> response = new HashMap<>();
                response.put("message", "Merchandise Order booked!");
                return response;
        }

        public List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(Long userId) {
                return merchandiseOrderRepository.findByUser_Id(userId).stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public List<MerchandiseOrderResponseDto> getAllMerchandiseOrders() {
                return merchandiseOrderRepository.findAll().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(Long stadiumId) {
                return merchandiseOrderRepository.findByStadium_Id(stadiumId).stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        public MerchandiseOrderResponseDto getMerchandiseOrderById(Long id) {
                MerchandiseOrder order = merchandiseOrderRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "MerchandiseOrder not found with id " + id));
                return convertToDto(order);
        }

}

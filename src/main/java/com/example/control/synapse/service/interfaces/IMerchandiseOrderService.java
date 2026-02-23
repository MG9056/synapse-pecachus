package com.example.control.synapse.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;

public interface IMerchandiseOrderService {
    Map<String, String> bookMerchandiseOrder(List<Long> merchandiseIdlist, Long userId, float price, Long seatId,
            Long stadiumId, Long eventId, LocalDateTime orderTime);

    List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(Long userId);

    List<MerchandiseOrderResponseDto> getAllMerchandiseOrders();

    List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(Long stadiumId);

    MerchandiseOrderResponseDto getMerchandiseOrderById(Long id);
}

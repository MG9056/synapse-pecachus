package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;

public interface IMerchandiseOrderService {
    List<MerchandiseOrderResponseDto> getAllMerchandiseOrders();

    MerchandiseOrderResponseDto getMerchandiseOrderById(Long id);

    Map<String, String> bookMerchandiseOrder(List<Long> merchandiseIdList, Long userId, Double price,
            Long seatId, Long stadiumId, Long eventId, String orderTime);

    List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(Long userId);

    List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(Long stadiumId);
}

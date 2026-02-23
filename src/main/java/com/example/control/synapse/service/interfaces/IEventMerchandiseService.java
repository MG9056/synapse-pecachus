package com.example.control.synapse.service.interfaces;

import java.util.List;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;

public interface IEventMerchandiseService {
    List<EventMerchandiseResponseDto> getAllEventMerchandise();

    EventMerchandiseResponseDto getEventMerchandiseById(Long id);

    List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(Long merchandiseOrderId);

    List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(Long stadiumId);

    List<EventMerchandiseResponseDto> getEventMerchandiseByEventId(Long eventId);
}

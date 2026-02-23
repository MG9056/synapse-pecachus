package com.example.control.synapse.service.interfaces;

import java.util.List;

import com.example.control.synapse.dto.response.EventMerchandiseResponseDto;

public interface IEventMerchandiseService {
    EventMerchandiseResponseDto getEventMerchandiseById(Long merchandiseId);

    List<EventMerchandiseResponseDto> getEventMerchandiseByMerchandiseOrderId(Long merchandiseOrderId);

    List<EventMerchandiseResponseDto> getEventMerchandiseByStadiumId(Long stadiumId);

    List<EventMerchandiseResponseDto> getAllEventMerchandise();

    List<EventMerchandiseResponseDto> getEventMerchandiseByEventId(Long eventId);
}

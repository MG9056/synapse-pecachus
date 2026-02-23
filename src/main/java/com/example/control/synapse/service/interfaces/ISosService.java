package com.example.control.synapse.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.response.SosResponseDto;

public interface ISosService {
    Map<String, String> raiseSos(String alertType, String message, Boolean isActive, LocalDateTime timeStamp,
            Long userId, Long eventId, Long stadiumId);

    Map<String, String> resolveSOS(Long sosId);

    List<SosResponseDto> getAllSos();

    List<SosResponseDto> getSosByUserId(Long userId);

    SosResponseDto getSosById(Long id);

    List<SosResponseDto> getSosByEventId(Long eventId);

    List<SosResponseDto> getSosByStadiumId(Long stadiumId);
}

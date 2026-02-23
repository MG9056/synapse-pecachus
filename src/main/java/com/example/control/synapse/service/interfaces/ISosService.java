package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.SosResponseDto;

public interface ISosService {
    List<SosResponseDto> getAllSos();

    SosResponseDto getSosById(Long id);

    List<SosResponseDto> getSosByUserId(Long userId);

    List<SosResponseDto> getSosByEventId(Long eventId);

    List<SosResponseDto> getSosByStadiumId(Long stadiumId);

    Map<String, String> raiseSos(String type, Long userId, Long eventId);

    Map<String, String> deleteSos(Long id, DeleteCredentialsDto deleteCredentialsDto);
}

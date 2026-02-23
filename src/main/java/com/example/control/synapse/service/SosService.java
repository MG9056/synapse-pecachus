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

import com.example.control.synapse.models.Sos;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.dto.response.SosResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.SosRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.service.interfaces.ISosService;

@Service
public class SosService implements ISosService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SosRepository sosRepository;
    private final StadiumRepository stadiumRepository;

    public SosService(UserRepository userRepository, EventRepository eventRepository, SosRepository sosRepository,
            StadiumRepository stadiumRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.sosRepository = sosRepository;
        this.stadiumRepository = stadiumRepository;
    }

    private SosResponseDto convertToDto(Sos sos) {
        SosResponseDto dto = new SosResponseDto();
        dto.setId(sos.getId());
        dto.setAlertType(sos.getAlertType());
        dto.setMessage(sos.getMessage());
        dto.setIsActive(sos.getIsActive());
        dto.setTimeStamp(sos.getTimeStamp());
        dto.setUserId(sos.getUser() != null ? sos.getUser().getId() : null);
        dto.setEventId(sos.getEvent() != null ? sos.getEvent().getId() : null);
        dto.setStadiumId(sos.getStadium() != null ? sos.getStadium().getId() : null);
        return dto;
    }

    @Transactional
    public Map<String, String> raiseSos(String alertType, String message, Boolean isActive, LocalDateTime timeStamp,
            Long userId, Long eventId, Long stadiumId) {
        Sos sos = new Sos();
        sos.setAlertType(alertType);
        sos.setMessage(message);
        sos.setIsActive(isActive);
        sos.setTimeStamp(timeStamp);

        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id " + eventId));

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        sos.setUser(user);
        sos.setEvent(event);
        sos.setStadium(stadium);

        sosRepository.save(sos);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Sos raised successfully!");
        return response;
    }

    @Transactional
    public Map<String, String> resolveSOS(Long sosId) {
        Sos sos = sosRepository.findById(sosId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SOS not found with id " + sosId));

        sos.setIsActive(false);
        sosRepository.save(sos);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Sos resolved successfully!");
        return response;
    }

    public List<SosResponseDto> getAllSos() {
        return sosRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SosResponseDto> getSosByUserId(Long userId) {
        return sosRepository.findByUser_Id(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SosResponseDto getSosById(Long id) {
        Sos sos = sosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SOS not found with id " + id));
        return convertToDto(sos);
    }

    public List<SosResponseDto> getSosByEventId(Long eventId) {
        return sosRepository.findByEvent_Id(eventId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SosResponseDto> getSosByStadiumId(Long stadiumId) {
        return sosRepository.findByStadium_Id(stadiumId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

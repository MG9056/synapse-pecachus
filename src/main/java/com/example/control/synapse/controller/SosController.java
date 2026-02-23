package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.SosRequest;
import com.example.control.synapse.dto.response.SosResponseDto;
import com.example.control.synapse.service.interfaces.ISosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sos")
@RequiredArgsConstructor
public class SosController {

    private final ISosService sosService;

    @PostMapping("/raiseSos")
    public Map<String, String> raiseSos(@RequestBody SosRequest sosRequest) {
        return sosService.raiseSos(
                sosRequest.getAlertType(),
                sosRequest.getMessage(),
                sosRequest.getIsActive(),
                sosRequest.getTimeStamp(),
                sosRequest.getUserId(),
                sosRequest.getEventId(),
                sosRequest.getStadiumId());
    }

    @PatchMapping("/{id}/resolveSos")
    public Map<String, String> resolveSos(@PathVariable Long id) {
        return sosService.resolveSOS(id);
    }

    @GetMapping("/{id}")
    public SosResponseDto getSosById(@PathVariable Long id) {
        return sosService.getSosById(id);
    }

    @GetMapping("/allSos")
    public List<SosResponseDto> getAllSos() {
        return sosService.getAllSos();
    }

    @GetMapping("/user/{userId}")
    public List<SosResponseDto> getSosByUserId(@PathVariable Long userId) {
        return sosService.getSosByUserId(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<SosResponseDto> getSosByEventId(@PathVariable Long eventId) {
        return sosService.getSosByEventId(eventId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<SosResponseDto> getSosByStadiumId(@PathVariable Long stadiumId) {
        return sosService.getSosByStadiumId(stadiumId);
    }

}

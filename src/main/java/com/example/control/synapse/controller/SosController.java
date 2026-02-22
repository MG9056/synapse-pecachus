package com.example.control.synapse.controller;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.SosRequest;
import com.example.control.synapse.dto.response.SosResponseDto;
import com.example.control.synapse.models.Sos;
import com.example.control.synapse.repository.SosRepository;
import com.example.control.synapse.service.SosService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/sos")
public class SosController {

    private final SosRepository sosRepository;

    private final SosService sosService;

    public SosController(SosService sosService, SosRepository sosRepository)
    {
        this.sosService= sosService;
        this.sosRepository = sosRepository;
    }
    

    @PostMapping("/raiseSos")
    public Map<String,String> raiseSos(@RequestBody SosRequest sosRequest)
    {
        return sosService.raiseSos(
            sosRequest.getAlertType(),

    sosRequest.getMessage(),
    sosRequest.getIsActive(),
    sosRequest.getTimeStamp(),
    sosRequest.getUserId(),
    sosRequest.getEventId(),
    sosRequest.getStadiumId()

        );



    }

    @PatchMapping("/{id}/resolveSos")
    public Map<String,String> resolveSos(@PathVariable Long id)
    {
        return sosService.resolveSOS(id);
    }

    @GetMapping("/id")
    public SosResponseDto getSosById(Long id)
    {
        return sosService.getSosById(id);
    }

    @GetMapping("/allSos")
    public List<Sos> getAllSos() {
        return sosRepository.findAll(); 
    }

    @GetMapping("/user/{userId}")
    public List<Sos> getSosByUserId(@PathVariable Long userId) {
        return sosRepository.findByUser_Id(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Sos> getSosByEventId(@PathVariable Long eventId) {
        return sosRepository.findByEvent_Id(eventId);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<Sos> getSosByStadiumId(@PathVariable Long stadiumId)
    {
        return sosRepository.findByStadium_Id(stadiumId);
    }
    
    


    
}

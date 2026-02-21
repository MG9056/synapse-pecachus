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
import com.example.control.synapse.service.SosService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/sos")
public class SosController {

    private final SosService sosService;

    public SosController(SosService sosService)
    {
        this.sosService= sosService;
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
    sosRequest.getEventId()

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
    
    


    
}

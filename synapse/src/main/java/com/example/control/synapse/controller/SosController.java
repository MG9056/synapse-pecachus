package com.example.control.synapse.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.SosRequest;
import com.example.control.synapse.service.SosService;

@RestController
@RequestMapping("/Sos")
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
    public void resolveSos(@PathVariable Long id)
    {
        sosService.resolveSOS(id);
    }


    
}

package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.control.synapse.models.Sos;
import com.example.control.synapse.models.User;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.SosRepository;
import com.example.control.synapse.repository.UserRepository;



public class SosService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SosRepository sosRepository;
    private final LocationService locationService;

    public SosService(UserRepository userRepository, EventRepository eventRepository, SosRepository sosRepository, LocationService locationService)
    {
        this.userRepository= userRepository;
        this.eventRepository= eventRepository;
        this.sosRepository=sosRepository;
        this.locationService= locationService;
    }

    public Map<String,String> raiseSos (String alertType, String message, Boolean isActive, LocalDateTime timeStamp, Long userId, Long eventId)
    {
        Sos sos= new Sos();

        sos.setAlertType(alertType);
        sos.setMessage(message);
        sos.setIsActive(isActive);
        sos.setTimeStamp(timeStamp);
        
        User user= userRepository.findById(userId).orElseThrow();
        Event event= eventRepository.findById(eventId).orElseThrow();

        sos.setUserId(user);
        sos.setEventId(event);

        sosRepository.save(sos);

            locationService.startSOSTracking(userId);


        Map<String,String> response = new HashMap<>();
        response.put("message", "Sos raised successfully!");
        return response;





        
    }

    public void resolveSOS(Long sosId) {

    Sos sos = sosRepository.findById(sosId)
            .orElseThrow();

    sos.setIsActive(false);
    sosRepository.save(sos);

    Long userId= sos.getUserId().getId();

    
    locationService.stopSOSTracking(userId);
}


    
}

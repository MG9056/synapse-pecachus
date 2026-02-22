package com.example.control.synapse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.control.synapse.models.Sos;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.dto.response.SosResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.SosRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;


@Service
public class SosService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SosRepository sosRepository;
    private final StadiumRepository stadiumRepository;

    public SosService(UserRepository userRepository, EventRepository eventRepository, SosRepository sosRepository, StadiumRepository stadiumRepository)
    {
        this.userRepository= userRepository;
        this.eventRepository= eventRepository;
        this.sosRepository=sosRepository;
        this.stadiumRepository=stadiumRepository;
     
    }

    public Map<String,String> raiseSos (String alertType, String message, Boolean isActive, LocalDateTime timeStamp, Long userId, Long eventId, Long stadiumId)
    {
        Sos sos= new Sos();

        sos.setAlertType(alertType);
        sos.setMessage(message);
        sos.setIsActive(isActive);
        sos.setTimeStamp(timeStamp);
        
        
        User user= userRepository.findById(userId).orElseThrow();
        Event event= eventRepository.findById(eventId).orElseThrow();
        Stadium stadium=stadiumRepository.findById(stadiumId).orElseThrow();

        sos.setUser(user);
        sos.setEvent(event);
        sos.setStadium(stadium);

        sosRepository.save(sos);

            // locationService.startSOSTracking(userId);


        Map<String,String> response = new HashMap<>();
        response.put("message", "Sos raised successfully!");
        return response;





        
    }

    public Map<String,String> resolveSOS(Long sosId) {

    Sos sos = sosRepository.findById(sosId)
            .orElseThrow();

    sos.setIsActive(false);
    sosRepository.save(sos);
    Map<String,String> response = new HashMap<>();
        response.put("message", "Sos resolved successfully!");
        return response;




    // Long userId= sos.getUserId().getId();

    
    // locationService.stopSOSTracking(userId);
}

public List<SosResponseDto> getAllSos()
{List<Sos> sosList= sosRepository.findAll();
    List<SosResponseDto> dtoList=  new ArrayList<>();

    for(Sos sos: sosList)
    { SosResponseDto sosResponseDto= new SosResponseDto();

        sosResponseDto.setId(sos.getId());
        sosResponseDto.setAlertType(sos.getAlertType());
        sosResponseDto.setMessage(sos.getMessage());
        sosResponseDto.setIsActive(sos.getIsActive());
        sosResponseDto.setTimeStamp(sos.getTimeStamp());
        sosResponseDto.setUserId(sos.getUser().getId());
        sosResponseDto.setEventId(sos.getEvent().getId());

        dtoList.add(sosResponseDto);




    }

    return dtoList;




}

public List<SosResponseDto> getSosByUserId(Long userId)
{List<Sos> sosList= sosRepository.findByUser_Id(userId);
    List<SosResponseDto> dtoList=  new ArrayList<>();

    for(Sos sos: sosList)
    { SosResponseDto sosResponseDto= new SosResponseDto();

        sosResponseDto.setId(sos.getId());
        sosResponseDto.setAlertType(sos.getAlertType());
        sosResponseDto.setMessage(sos.getMessage());
        sosResponseDto.setIsActive(sos.getIsActive());
        sosResponseDto.setTimeStamp(sos.getTimeStamp());
        sosResponseDto.setUserId(sos.getUser().getId());
        sosResponseDto.setEventId(sos.getEvent().getId());

        dtoList.add(sosResponseDto);




    }

    return dtoList;




}

public SosResponseDto getSosById(Long id)
{Sos sos= sosRepository.findById(id).orElseThrow();

    SosResponseDto sosResponseDto= new SosResponseDto();

        sosResponseDto.setId(sos.getId());
        sosResponseDto.setAlertType(sos.getAlertType());
        sosResponseDto.setMessage(sos.getMessage());
        sosResponseDto.setIsActive(sos.getIsActive());
        sosResponseDto.setTimeStamp(sos.getTimeStamp());
        sosResponseDto.setUserId(sos.getUser().getId());
        sosResponseDto.setEventId(sos.getEvent().getId());

        return sosResponseDto;


}

public List<SosResponseDto> getSosByEventId(Long eventId)
{List<Sos> sosList= sosRepository.findByEvent_Id(eventId);
    List<SosResponseDto> dtoList=  new ArrayList<>();

    for(Sos sos: sosList)
    { SosResponseDto sosResponseDto= new SosResponseDto();

        sosResponseDto.setId(sos.getId());
        sosResponseDto.setAlertType(sos.getAlertType());
        sosResponseDto.setMessage(sos.getMessage());
        sosResponseDto.setIsActive(sos.getIsActive());
        sosResponseDto.setTimeStamp(sos.getTimeStamp());
        sosResponseDto.setUserId(sos.getUser().getId());
        sosResponseDto.setEventId(sos.getEvent().getId());

        dtoList.add(sosResponseDto);




    }

    return dtoList;




}




    
}

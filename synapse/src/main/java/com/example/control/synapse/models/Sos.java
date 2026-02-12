package com.example.control.synapse.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


public class Sos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String alertType;
    private String message;
    private Boolean isActive;
    private LocalDateTime timeStamp;

    @ManyToOne
    private User userid;

    Sos(){}

    Sos(String alertType, String message, Boolean isActive, LocalDateTime timeStamp )
    {
        this.alertType=alertType;
        this.message=message;
        this.isActive= isActive;
        this.timeStamp=timeStamp;
    }


    
    
    
}

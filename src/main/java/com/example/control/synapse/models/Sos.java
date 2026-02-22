package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Sos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alertType;
    private String message;
    private Boolean isActive;
    private LocalDateTime timeStamp;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

   

    public Sos(){}

    Sos(String alertType, String message, Boolean isActive, LocalDateTime timeStamp, User user, Event event )
    {
        this.alertType=alertType;
        this.message=message;
        this.isActive= isActive;
        this.timeStamp=timeStamp;
        this.user=user;
        this.event=event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

     public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    


    
    
    
}

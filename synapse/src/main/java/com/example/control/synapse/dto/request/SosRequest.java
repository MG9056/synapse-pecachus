package com.example.control.synapse.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


public class SosRequest {
   

    private String alertType;
    private String message;
    private Boolean isActive;
    private LocalDateTime timeStamp;
    private long userId;
    private long eventId;

   

    SosRequest(){}

    SosRequest(String alertType, String message, Boolean isActive, LocalDateTime timeStamp, Long userId, Long eventId )
    {
        this.alertType=alertType;
        this.message=message;
        this.isActive= isActive;
        this.timeStamp=timeStamp;
        this.userId=userId;
        this.eventId= eventId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserid(Long userId) {
        this.userId = userId;
    }

     public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    
    


    
    
    
}

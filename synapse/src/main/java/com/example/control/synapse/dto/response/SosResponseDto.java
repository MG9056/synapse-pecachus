package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;


public class SosResponseDto {
  

    private Long id;

    private String alertType;
    private String message;
    private Boolean isActive;
    private LocalDateTime timeStamp;

    
    private Long userId;

   
    private Long eventId;

   

    public SosResponseDto(){}

    SosResponseDto(Long id,String alertType, String message, Boolean isActive, LocalDateTime timeStamp, Long userId, Long eventId )
    {   this.id=id;
        this.alertType=alertType;
        this.message=message;
        this.isActive= isActive;
        this.timeStamp=timeStamp;
        this.userId=userId;
        this.eventId=eventId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

     public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    


    
    
    
}

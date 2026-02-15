package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;

import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.User;




public class BookingResponseDto {

  

     private Long id;
   
     private User userId;

     private Event eventId;
     private LocalDateTime bookingTime;

     public BookingResponseDto(){}

     public BookingResponseDto(User user, Event event, LocalDateTime datetime)
     {this.userId=user;
        this.eventId=event;
        this.bookingTime=datetime;




  

     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public User getUserId() {
         return userId;
     }

     public void setUserId(User user) {
         this.userId = user;
     }

     public Event getEventId() {
         return eventId;
     }

     public void setEventId(Event event) {
         this.eventId = event;
     }

     public LocalDateTime getBookingTime() {
         return bookingTime;
     }

     public void setBookingTime(LocalDateTime datetime) {
         this.bookingTime = datetime;
     }

     







    
}

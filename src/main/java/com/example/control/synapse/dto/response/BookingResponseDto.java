package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;






public class BookingResponseDto {

  

     private Long id;
   
     private Long userId;

     private Long eventId;
     private LocalDateTime bookingTime;

     public BookingResponseDto(){}

     public BookingResponseDto(Long id,Long user, Long event, LocalDateTime datetime)
     {  this.id=id;
        this.userId=user;
        this.eventId=event;
        this.bookingTime=datetime;




  

     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Long getUserId() {
         return userId;
     }

     public void setUserId(Long user) {
         this.userId = user;
     }

     public Long getEventId() {
         return eventId;
     }

     public void setEventId(Long event) {
         this.eventId = event;
     }

     public LocalDateTime getBookingTime() {
         return bookingTime;
     }

     public void setBookingTime(LocalDateTime datetime) {
         this.bookingTime = datetime;
     }

     







    
}

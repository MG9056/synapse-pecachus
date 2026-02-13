package com.example.control.synapse.models;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Booking {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     private Long id;
     @ManyToOne
     private User userId;

     @ManyToOne
     private Event eventId;
     private LocalDateTime bookingTime;

     public Booking(){}

     public Booking(User user, Event event, LocalDateTime datetime)
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

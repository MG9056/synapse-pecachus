package com.example.control.synapse.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @ManyToOne
     private User user;

     @ManyToOne
     private Event event;
     private LocalDateTime bookingTime;

     public Booking(){}

     public Booking(User user, Event event, LocalDateTime datetime)
     {this.user=user;
        this.event=event;
        this.bookingTime=datetime;






     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public User getUserId() {
         return user;
     }

     public void setUserId(User user) {
         this.user = user;
     }

     public Event getEventId() {
         return event;
     }

     public void setEventId(Event event) {
         this.event = event;
     }

     public LocalDateTime getBookingTime() {
         return bookingTime;
     }

     public void setBookingTime(LocalDateTime datetime) {
         this.bookingTime = datetime;
     }

     







    
}

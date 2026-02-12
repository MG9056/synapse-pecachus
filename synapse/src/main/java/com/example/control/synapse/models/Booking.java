package com.example.control.synapse.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Booking {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     private Long id;
     @ManyToOne
     private User user;

     @ManyToOne
     private Event event;
     private Boolean status;

     public Booking(){}

     public Booking(User user, Event event, Boolean status)
     {this.user=user;
        this.event=event;
        this.status=status;





     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public User getUser() {
         return user;
     }

     public void setUser(User user) {
         this.user = user;
     }

     public Event getEvent() {
         return event;
     }

     public void setEvent(Event event) {
         this.event = event;
     }

     public Boolean getStatus() {
         return status;
     }

     public void setStatus(Boolean status) {
         this.status = status;
     }

     







    
}


package com.example.control.synapse.models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MerchandiseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private double price;
    @ManyToOne
    private Seat seat;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Stadium stadium;

    @ManyToOne
    private Event event;

    private LocalDateTime orderTime;

    public MerchandiseOrder(){}
    public MerchandiseOrder(double price, Seat seat, User user, Stadium stadiumId, Event event, LocalDateTime orderTime)
    {
        this.user=user;
        this.price=price;
        this.seat=seat;
        this.stadium=stadiumId;
        this.event=event;
        this.orderTime=orderTime;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Seat getSeat() {
        return seat;
    }
     public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Stadium getStadium() {
        return stadium;
    }
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
     }

      
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

     public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

     
     
    
}

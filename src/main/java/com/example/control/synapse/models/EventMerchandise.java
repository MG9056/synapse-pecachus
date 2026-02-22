package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EventMerchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    @ManyToOne
    private Stadium stadium;
    @ManyToOne
    private MerchandiseOrder merchandiseOrder;

    @ManyToOne
    private Event event;
  


    public EventMerchandise() {}
    public EventMerchandise(String name, String description, double price, double rating, Stadium stadiumId, MerchandiseOrder merchandiseOrderId, Event event) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadium = stadiumId;
        this.merchandiseOrder= merchandiseOrderId;
        this.event=event;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public Stadium getStadium() {
        return stadium;
    }
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public MerchandiseOrder getMerchandiseOrder() {
        return merchandiseOrder;
    }
    public void setMerchandiseOrder(MerchandiseOrder merchandiseOrderId) {
        this.merchandiseOrder = merchandiseOrderId;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    
    

}

package com.example.control.synapse.models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stadium stadium;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private Double minPrice;
    private String category;
    private Boolean live;
    public Event() {}
    public Event(Stadium stadiumId, String name, LocalDateTime dateTime, String description,Double minPrice,String category,Boolean live) {
        this.stadium = stadiumId;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.minPrice=minPrice;
        this.category=category;
        this.live=live;
    
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Stadium getStadium() {
        return stadium;
    }
    public void setStadium(Stadium stadiumId) {
        this.stadium = stadiumId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Boolean getLive() {
        return live;
    }
    public void setLive(Boolean live) {
        this.live = live;
    }
    
    
}

package com.example.control.synapse.models;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stadium stadiumId;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    public Event() {}
    public Event(Stadium stadiumId, String name, LocalDateTime dateTime, String description) {
        this.stadiumId = stadiumId;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Stadium getStadiumId() {
        return stadiumId;
    }
    public void setStadiumId(Stadium stadiumId) {
        this.stadiumId = stadiumId;
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
    
}

package com.example.control.synapse.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stadium stadiumId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event eventId;
    private String content;
    private double rating;
    public Feedback() {}
    public Feedback(Stadium stadiumId, User user, Event eventId, String content, double rating) {
        this.stadiumId = stadiumId;
        this.user = user;
        this.eventId = eventId;
        this.content = content;
        this.rating = rating;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Event getEventId() {
        return eventId;
    }
    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    
}

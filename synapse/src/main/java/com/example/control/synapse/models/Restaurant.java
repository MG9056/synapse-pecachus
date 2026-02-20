package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double rating;

    @ManyToOne
    private Stadium stadium;

    public Restaurant(){}

    public Restaurant(String name, double rating, Stadium stadium)
    {this.name=name;
        this.rating= rating;
        this.stadium=stadium;
        
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadiumId(Stadium stadium) {
        this.stadium = stadium;
    }

    

    
}

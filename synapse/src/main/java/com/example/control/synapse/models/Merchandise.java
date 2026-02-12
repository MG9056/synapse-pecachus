package com.example.control.synapse.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    @ManyToOne
    private Stadium stadium;
    public Merchandise() {}
    public Merchandise(String name, String description, double price, double rating, Stadium stadium) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadium = stadium;
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
    
    

}

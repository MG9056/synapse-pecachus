package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
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

    private String type;
    private String size;
    private Integer stock;


    public Merchandise() {}
    public Merchandise(String name, String description, double price, double rating, Stadium stadiumId, String type, String size, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadium = stadiumId;
        this.type=type;
        this.size=size;
        this.stock=stock;
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

    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    

}

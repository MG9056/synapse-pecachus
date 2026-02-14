package com.example.control.synapse.dto.response;

import com.example.control.synapse.models.Stadium;


public class RestaurantResponseDto {



    private Long id;
    private String name;
    private double rating;
    private Stadium stadiumId;

    public RestaurantResponseDto(){}

    public RestaurantResponseDto(String name, double rating, Stadium stadiumId)
    {this.name=name;
        this.rating= rating;
        this.stadiumId=stadiumId;
        
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

    public Stadium getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Stadium stadiumId) {
        this.stadiumId = stadiumId;
    }

    

    
}

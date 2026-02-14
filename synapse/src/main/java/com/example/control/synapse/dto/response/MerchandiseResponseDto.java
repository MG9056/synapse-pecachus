package com.example.control.synapse.dto.response;

import com.example.control.synapse.models.Stadium;


public class MerchandiseResponseDto {
   
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
   
    private Stadium stadiumId;

    public MerchandiseResponseDto() {}
    public MerchandiseResponseDto(Long id,String name, String description, double price, double rating, Stadium stadiumId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadiumId = stadiumId;
        this.id=id;
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
    public Stadium getStadiumId() {
        return stadiumId;
    }
    public void setStadiumId(Stadium stadium) {
        this.stadiumId = stadium;
    }
    
    

}

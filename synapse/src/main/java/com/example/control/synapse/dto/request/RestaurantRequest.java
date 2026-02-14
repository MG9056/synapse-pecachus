package com.example.control.synapse.dto.request;

public class RestaurantRequest {

    private String name ;
    private double rating;
    private Long stadiumId;

    public RestaurantRequest(){}
    
    public RestaurantRequest(String name, Double rating, Long stadiumId)
    {
        this.name=name;
        this.rating=rating;
        this.stadiumId=stadiumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }
}

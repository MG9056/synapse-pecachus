package com.example.control.synapse.dto.response;




public class RestaurantResponseDto {



    private Long id;
    private String name;
    private double rating;
    private Long stadiumId;

    public RestaurantResponseDto(){}

    public RestaurantResponseDto(Long id,String name, double rating, Long stadiumId)
    {   this.id=id;
        this.name=name;
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

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    

    
}

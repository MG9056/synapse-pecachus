package com.example.control.synapse.dto.request;


public class RestaurantUpdateDto {

    
    private String name;
    private Double rating;
    private Long stadiumId;

    public RestaurantUpdateDto(){}

    public RestaurantUpdateDto(String name, Double rating, Long stadiumId)
    {   this.name=name;
        this.rating= rating;
        this.stadiumId=stadiumId;
        
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    

    
}

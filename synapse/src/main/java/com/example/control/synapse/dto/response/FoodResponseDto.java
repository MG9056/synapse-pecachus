package com.example.control.synapse.dto.response;


import com.example.control.synapse.models.Restaurant;


public class FoodResponseDto {
    

    private Long id;
    private String name;
    private Restaurant restaurantId;
    private float price;
    private float rating;
    


    public FoodResponseDto(){}

    public FoodResponseDto( String name, Restaurant restaurant, float price, float rating)
    { this.name=name;
        this.restaurantId= restaurant;
        this.price= price;
        this.rating=rating;
        

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

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurant) {
        this.restaurantId = restaurant;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


  
    




    
}

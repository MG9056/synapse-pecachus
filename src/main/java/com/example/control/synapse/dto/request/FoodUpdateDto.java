package com.example.control.synapse.dto.request;

public class FoodUpdateDto {
   

    
    private String name;
    private Long restaurantId;
    private float price;
    private float rating;
    


    public FoodUpdateDto(){}

    public FoodUpdateDto(String name, Long restaurantId, float price, float rating)
    { this.name=name;
        this.restaurantId= restaurantId;
        this.price= price;
        this.rating=rating;
        

    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

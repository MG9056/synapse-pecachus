package com.example.control.synapse.dto.response;





public class FoodResponseDto {
    

    private Long id;
    private String name;
    private Long restaurantId;
    private float price;
    private float rating;
    


    public FoodResponseDto(){}

    public FoodResponseDto( Long id,String name, Long restaurant, float price, float rating)
    {   this.id=id;
        this.name=name;
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurant) {
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

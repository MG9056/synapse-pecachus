package com.example.control.synapse.dto.request;

public class FoodUpdateDto {
   

    
    private String name;
    private Long restaurantId;
    private Float price;
    private Float rating;
    private String type;
    private String diet;
    private Integer stock;
    

    public FoodUpdateDto(){}

    public FoodUpdateDto(String name, Long restaurantId, Float price, Float rating, String type, String diet, Integer stock)
    { this.name=name;
        this.restaurantId= restaurantId;
        this.price= price;
        this.rating=rating;
        this.type=type;
        this.diet=diet;
        this.stock=stock;
        

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


  
    




    
}

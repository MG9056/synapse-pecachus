package com.example.control.synapse.dto.request;

public class FoodRequest
{
    private String name;
    private Long restaurantId;
    private float price;
    private float rating;
    private String type;
    private String diet;
    private Integer stock;


    public FoodRequest(){}

    public FoodRequest(String name, Long restaurantId, float price, float rating, String type, String diet, Integer stock)
    {
        this.name=name;
        this.restaurantId=restaurantId;
        this.price=price;
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
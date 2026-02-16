package com.example.control.synapse.dto.response;

import com.example.control.synapse.models.FoodOrder;
import com.example.control.synapse.models.Restaurant;



public class EventFoodResponseDto{

private Long id;
private String name;
private Restaurant restaurantId;

private float price;
private float rating;
private FoodOrder orderId;


public EventFoodResponseDto(){}
public EventFoodResponseDto(String name, Restaurant restaurantId, float price, float rating, FoodOrder orderId)
{this.price=price;
this.restaurantId= restaurantId;
this.price=price;
this.rating=rating;
this.orderId=orderId;



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
public void setRestaurantId(Restaurant restaurantId) {
    this.restaurantId = restaurantId;
}


public float getRating() {
    return rating;
}
public void setRating(float rating) {
    this.rating = rating;
}
public FoodOrder getOrderId() {
    return orderId;
}
public void setOrderId(FoodOrder orderId) {
    this.orderId = orderId;
}


public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public float getPrice() {
    return price;
}
public void setPrice(float price) {
    this.price = price;
}








}
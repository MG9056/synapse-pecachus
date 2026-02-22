package com.example.control.synapse.dto.response;





public class EventFoodResponseDto{

private Long id;
private String name;
private Long restaurantId;

private float price;
private float rating;
private Long orderId;


public EventFoodResponseDto(){}
public EventFoodResponseDto(Long id,String name, Long restaurantId, float price, float rating, Long orderId)
{  this.id=id;
this.price=price;
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
public Long getRestaurantId() {
    return restaurantId;
}
public void setRestaurantId(Long restaurantId) {
    this.restaurantId = restaurantId;
}


public float getRating() {
    return rating;
}
public void setRating(float rating) {
    this.rating = rating;
}
public Long getOrderId() {
    return orderId;
}
public void setOrderId(Long orderId) {
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
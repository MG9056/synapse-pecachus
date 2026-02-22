package com.example.control.synapse.dto.request;




public class EventFoodRequest{

private Long id;
private String name;
private Long restaurantId;
private Long eventId;
private float rating;

private Long orderId;
private float price;


public EventFoodRequest(){}
public EventFoodRequest(float price, Long orderId)
{this.price=price;
this.orderId=orderId;


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
public Long getOrderId() {
    return orderId;
}
public void setOrderId(Long orderId) {
    this.orderId = orderId;
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
public Long getEventId() {
    return eventId;
}
public void setEventId(Long eventId) {
    this.eventId = eventId;
}
public float getRating() {
    return rating;
}
public void setRating(float rating) {
    this.rating = rating;
}






}
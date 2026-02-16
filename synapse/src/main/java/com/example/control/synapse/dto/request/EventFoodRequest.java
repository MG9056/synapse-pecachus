package com.example.control.synapse.dto.request;

import com.example.control.synapse.models.FoodOrder;


public class EventFoodRequest{

private Long id;

private float price;
private FoodOrder orderId;

public EventFoodRequest(){}
public EventFoodRequest(float price, FoodOrder orderId)
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
public FoodOrder getOrderId() {
    return orderId;
}
public void setOrderId(FoodOrder orderId) {
    this.orderId = orderId;
}







}
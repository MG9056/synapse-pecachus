package com.example.control.synapse.dto.request;

import com.example.control.synapse.models.Order;


public class EventFoodRequest{

private Long id;

private float price;
private Order orderId;

public EventFoodRequest(){}
public EventFoodRequest(float price, Order orderId)
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
public Order getOrderId() {
    return orderId;
}
public void setOrderId(Order orderId) {
    this.orderId = orderId;
}







}
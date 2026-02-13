package com.example.control.synapse.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class EventFood{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private float price;
@ManyToOne
private Order orderId;

public EventFood(){}
public EventFood(float price, Order orderId)
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
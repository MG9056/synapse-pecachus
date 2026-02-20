package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EventFood{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Restaurant restaurant;
    private float price;
    private float rating;
    @ManyToOne
    private FoodOrder order;


    public EventFood(){}
    public EventFood(String name, Restaurant restaurantId, float price, float rating, FoodOrder orderId)
    {this.price=price;
    this.restaurant= restaurantId;
    this.price=price;
    this.rating=rating;
    this.order=orderId;



    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurantId) {
        this.restaurant = restaurantId;
    }


    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public FoodOrder getOrder() {
        return order;
    }
    public void setOrder(FoodOrder orderId) {
        this.order = orderId;
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
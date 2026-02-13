package com.example.control.synapse.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @ManyToOne
    private Restaurant restaurantId;
    private float price;
    private float rating;
    @ManyToOne
    private Order orderId;


    public Food(){}

    public Food( String name, Restaurant restaurant, float price, float rating, Order order)
    { this.name=name;
        this.restaurantId= restaurant;
        this.price= price;
        this.rating=rating;
        this.orderId=order;

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

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurant) {
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

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order order) {
        this.orderId = order;
    }

    




    
}

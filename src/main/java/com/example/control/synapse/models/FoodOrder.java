
package com.example.control.synapse.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    @ManyToOne
    private Seat seat;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;


    
    public FoodOrder(){}
    public FoodOrder(double price, Seat seat, User user, Restaurant restaurantId)
     {
        this.user=user;
        this.price=price;
        this.seat=seat;
        this.restaurant=restaurantId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurantId) {

        this.restaurant = restaurantId;
     }

     
     
    
}

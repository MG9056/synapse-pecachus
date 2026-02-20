
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
    private Seat seatId;
    
    @ManyToOne
    private User userId;

    @ManyToOne
    private Restaurant restaurantId;


    
    public FoodOrder(){}
    public FoodOrder(double price, Seat seat, User user, Restaurant restaurantId)
     {
        this.userId=user;
        this.price=price;
        this.seatId=seat;
        this.restaurantId=restaurantId;
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
    public Seat getSeatId() {
        return seatId;
    }
    public void setSeatId(Seat seat) {
        this.seatId = seat;
    }
    public User getUserId() {
        return userId;
    }
    public void setUserId(User user) {
        this.userId = user;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Restaurant restaurantId) {

        this.restaurantId = restaurantId;
     }

     
     
    
}

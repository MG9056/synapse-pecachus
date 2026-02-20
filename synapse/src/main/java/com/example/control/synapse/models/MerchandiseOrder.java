
package com.example.control.synapse.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MerchandiseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private double price;
    @ManyToOne
    private Seat seat;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Stadium stadium;


    
    public MerchandiseOrder(){}
    public MerchandiseOrder(double price, Seat seat, User user, Stadium stadiumId)
    {
        this.user=user;
        this.price=price;
        this.seat=seat;
        this.stadium=stadiumId;
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
        return seat;
    }
     public void setSeatId(Seat seat) {
        this.seat = seat;
    }
    public User getUserId() {
        return user;
    }
    public void setUserId(User user) {
        this.user = user;
    }

    public Stadium getStadiumId() {
        return stadium;
    }
    public void setStadiumId(Stadium stadiumId) {
        this.stadium = stadiumId;
     }

     
     
    
}

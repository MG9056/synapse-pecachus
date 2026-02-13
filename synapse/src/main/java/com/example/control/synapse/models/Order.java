
package com.example.control.synapse.models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Order {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     private Long id;

     private double price;
     @ManyToOne
     private Seat seatId;
    
     @ManyToOne
     private User userId;


     public Order(){}
     public Order(double price, Seat seat, User user)
     {
        this.userId=user;
        this.price=price;
        this.seatId=seat;
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

     
     
    
}

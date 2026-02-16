
package com.example.control.synapse.dto.response;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class MerchandiseOrderResponseDto {

  

     private Long id;
     private double price;
     private Seat seatId;
     private User userId;
     private Stadium stadiumId;


     
     public MerchandiseOrderResponseDto(){}
     public MerchandiseOrderResponseDto(double price, Seat seat, User user, Stadium stadiumId)
     {
        this.userId=user;
        this.price=price;
        this.seatId=seat;
        this.stadiumId= stadiumId;
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

     public Stadium getStadiumId() {
        return stadiumId;
    }
     public void setStadiumId(Stadium stadiumId) {
         this.stadiumId = stadiumId;
     }

     
     
    
}

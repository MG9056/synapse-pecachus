
package com.example.control.synapse.dto.response;



public class MerchandiseOrderResponseDto {

  

     private Long id;
     private double price;
     private Long seatId;
     private Long userId;
     private Long stadiumId;


     
     public MerchandiseOrderResponseDto(){}
     public MerchandiseOrderResponseDto(Long id,double price, Long seat, Long user, Long stadiumId)
     {  this.id=id;
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
     public Long getSeatId() {
         return seatId;
     }
     public void setSeatId(Long seat) {
         this.seatId = seat;
     }
     public Long getUserId() {
         return userId;
     }
     public void setUserId(Long user) {
         this.userId = user;
     }

     public Long getStadiumId() {
        return stadiumId;
    }
     public void setStadiumId(Long stadiumId) {
         this.stadiumId = stadiumId;
     }

     
     
    
}

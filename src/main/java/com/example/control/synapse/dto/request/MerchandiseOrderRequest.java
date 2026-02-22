package com.example.control.synapse.dto.request;
import java.time.LocalDateTime;
import java.util.List;



public class MerchandiseOrderRequest {
        private List<Long> merchandiseIdlist;
        private Long userId;
        private float price;
        private Long seatId;
        private Long stadiumId;
        private Long eventId;
        private LocalDateTime orderTime;

    

        public MerchandiseOrderRequest(){}

        public MerchandiseOrderRequest(List<Long>merchandiseIdlist, Long userId, float price, Long seatId, Long stadiumId, Long eventId, LocalDateTime orderTime)
        {
            this.merchandiseIdlist=merchandiseIdlist;
            this.userId=userId;
            this.price=price;
            this.seatId=seatId;
            this.stadiumId=stadiumId;
            this.eventId=eventId;
            this.orderTime=orderTime;
        }

        public List<Long> getMerchandiseIdlist() {
            return merchandiseIdlist;
        }
        public void setMerchandiseIdlist(List<Long> merchandiseIdlist) {
            this.merchandiseIdlist = merchandiseIdlist;
        }
        public Long getUserId() {
            return userId;
        }
        public void setUserId(Long userId) {
            this.userId = userId;
        }
        public float getPrice() {
            return price;
        }
        public void setPrice(float price) {
            this.price = price;
        }
        public Long getSeatId() {
            return seatId;
        }
        public void setSeatId(Long seatId) {
            this.seatId = seatId;
        }

        public Long getStadiumId() {
            return stadiumId;
        }

        public void setStadiumId(Long stadiumId) {
            this.stadiumId = stadiumId;
        }

         public Long getEventId() {
            return eventId;
        }

        public void setEventId(Long eventId) {
            this.eventId = eventId;
        }

        
        public LocalDateTime getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(LocalDateTime orderTime) {
            this.orderTime = orderTime;
        }


        

            
    
}

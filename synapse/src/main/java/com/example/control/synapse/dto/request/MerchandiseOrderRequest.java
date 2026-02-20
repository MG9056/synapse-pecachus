package com.example.control.synapse.dto.request;
import java.util.List;



public class MerchandiseOrderRequest {
        private List<Long> merchandiseIdlist;
        private Long userId;
        private float price;
        private Long seatId;
        private Long stadiumId;


        
        public MerchandiseOrderRequest(){}

        public MerchandiseOrderRequest(List<Long>merchandiseIdlist, Long userId, float price, Long seatId, Long stadiumId)
        {
            this.merchandiseIdlist=merchandiseIdlist;
            this.userId=userId;
            this.price=price;
            this.seatId=seatId;
            this.stadiumId=stadiumId;
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


        

            
    
}

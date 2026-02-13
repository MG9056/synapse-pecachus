package com.example.control.synapse.dto.request;
import java.util.List;

public class OrderRequest {
        private List<Long> foodIdlist;
        private Long userId;
        private float price;
        private Long seatId;
        public List<Long> getFoodIdlist() {
            return foodIdlist;
        }
        public void setFoodIdlist(List<Long> foodIdlist) {
            this.foodIdlist = foodIdlist;
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

        

            
    
}

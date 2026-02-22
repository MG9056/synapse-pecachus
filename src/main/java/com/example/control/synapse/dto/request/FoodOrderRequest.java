package com.example.control.synapse.dto.request;
import java.util.List;

public class FoodOrderRequest {
        private List<Long> foodIdlist;
        private Long userId;
        private float price;
        private Long seatId;
        private Long restaurantId;


        

        public FoodOrderRequest(){}

        public FoodOrderRequest(List<Long>foodIdlist, Long userId, float price, Long seatId, Long restaurantId)
        {
            this.foodIdlist=foodIdlist;
            this.userId=userId;
            this.price=price;
            this.seatId=seatId;
            this.restaurantId=restaurantId;
        }

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

        public Long getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
        }

        

            
    
}

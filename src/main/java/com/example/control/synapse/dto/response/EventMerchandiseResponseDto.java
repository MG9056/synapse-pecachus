package com.example.control.synapse.dto.response;




public class EventMerchandiseResponseDto {

    private Long id;
   
    
   
    private String name;
    private String description;
    private double price;
    private double rating;
   
    private Long stadiumId;
    private Long merchandiseOrderId;

   
    public EventMerchandiseResponseDto() {}
    public EventMerchandiseResponseDto(Long id, String name, String description, double price, double rating, Long stadiumId, Long merchandiseOrderId) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadiumId = stadiumId;
        this.merchandiseOrderId= merchandiseOrderId;
        
    }

     public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public Long getStadiumId() {
        return stadiumId;
    }
    public void setStadiumId(Long stadium) {
        this.stadiumId = stadium;
    }

     public Long getMerchandiseOrderId() {
        return merchandiseOrderId;
    }
    public void setMerchandiseOrderId(Long merchandiseOrderId) {
        this.merchandiseOrderId = merchandiseOrderId;
    }
    
    

}

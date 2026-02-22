package com.example.control.synapse.dto.request;





public class MerchandiseRequest {
   
    
    private String name;
    private String description;
    private double price;
    private double rating;
    private String type;
    private String size;
    private Integer stock;

    
    private Long stadiumId;
    public MerchandiseRequest() {}
    public MerchandiseRequest(String name, String description, double price, double rating, Long stadiumId, String type, String size, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.stadiumId = stadiumId;
        this.type=type;
        this.size=size;
        this.stock=stock;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    

}

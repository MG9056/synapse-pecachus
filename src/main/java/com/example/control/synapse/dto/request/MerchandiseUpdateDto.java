package com.example.control.synapse.dto.request;





public class MerchandiseUpdateDto {
   
    
    private String name;
    private String description;
    private Double price;
    private Double rating;
    private String type;
    private String size;
    private Integer stock;
 
    private Long stadiumId;
    public MerchandiseUpdateDto() {}
    public MerchandiseUpdateDto(String name, String description, Double price,Double rating, Long stadiumId, String type, String size, Integer stock) {
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
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

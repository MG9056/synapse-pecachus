package com.example.control.synapse.dto.response;









public class StadiumResponseDto {

  
    private Long id;
    private String city;
    private String state;
    private String country;
    private int capacity;

    public StadiumResponseDto(){}

    public StadiumResponseDto(Long id,String city, String state, String country, int capacity)
    {   this.id=id;
        this.city= city;
        this.state=state;
        this.country=country;
        this.capacity=capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    






    
    
}

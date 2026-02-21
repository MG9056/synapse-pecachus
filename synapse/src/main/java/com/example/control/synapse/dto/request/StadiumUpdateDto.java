package com.example.control.synapse.dto.request;

public class StadiumUpdateDto {

   

  

    private String city;
    private String state;
    private String country;
    private Integer capacity;

    public StadiumUpdateDto(){}

    public StadiumUpdateDto(String city, String state, String country, Integer capacity)
    {
        this.city=city;
        this.state=state;
        this.country=country;
        this.capacity=capacity;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    


    
}

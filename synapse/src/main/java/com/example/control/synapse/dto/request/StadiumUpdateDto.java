package com.example.control.synapse.dto.request;

public class StadiumUpdateDto {

    private Long stadiumId;

  

    private String city;
    private String state;
    private String country;
    private int capacity;

    public StadiumUpdateDto(){}

    public StadiumUpdateDto(String city, String state, String country, int capacity)
    {
        this.city=city;
        this.state=state;
        this.country=country;
        this.capacity=capacity;
    }


      public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
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

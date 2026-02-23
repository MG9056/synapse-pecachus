package com.example.control.synapse.dto.request;

public class StadiumUpdateDto {
    private String city;
    private String state;
    private String country;
    private Integer capacity;
    private String name;
    private String adminEmail;

    public StadiumUpdateDto() {
    }

    public StadiumUpdateDto(String city, String state, String country, Integer capacity, String name,
            String adminEmail) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.capacity = capacity;
        this.name = name;
        this.adminEmail = adminEmail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}

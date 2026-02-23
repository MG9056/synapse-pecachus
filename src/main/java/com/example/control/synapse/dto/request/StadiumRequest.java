package com.example.control.synapse.dto.request;

public class StadiumRequest {
    private String city;
    private String state;
    private String country;
    private int capacity;
    private String name;
    private String adminEmail;

    public StadiumRequest() {
    }

    public StadiumRequest(String city, String state, String country, int capacity, String name, String adminEmail) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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

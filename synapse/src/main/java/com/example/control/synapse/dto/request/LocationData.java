package com.example.control.synapse.dto.request;

public class LocationData {

    // Identifies which user this location belongs to
    // So the backend knows to update THIS user's entry in the map, not create a duplicate
    private Long userId;

    // GPS latitude coordinate from the user's phone
    private double latitude;

    // GPS longitude coordinate from the user's phone
    private double longitude;

    public LocationData() {}

    public LocationData(Long userId, double latitude, double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
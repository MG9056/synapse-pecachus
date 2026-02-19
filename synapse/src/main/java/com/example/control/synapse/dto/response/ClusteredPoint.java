package com.example.control.synapse.dto.response;

public class ClusteredPoint {

    // The center latitude of this cluster/bucket
    private double latitude;

    // The center longitude of this cluster/bucket
    private double longitude;

    // How many people are in this cluster
    // Higher weight = more people = rendered as red on the heatmap
    // Lower weight = fewer people = rendered as green on the heatmap
    private int weight;

    public ClusteredPoint() {}

    public ClusteredPoint(double latitude, double longitude, int weight) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
    }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}

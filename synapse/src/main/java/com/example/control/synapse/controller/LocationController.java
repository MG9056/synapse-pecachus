package com.example.control.synapse.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.control.synapse.dto.request.LocationData;
import com.example.control.synapse.service.LocationService;

@Controller
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // @MessageMapping("/location/update") means this method listens on the WebSocket destination
    // "/app/location/update" (Spring automatically prepends "/app" based on your WebSocket config)
    // When any user's app publishes to "/app/location/update", this method is triggered
    // Spring automatically deserializes the incoming JSON body into a LocationData object
    // e.g., { "userId": 1001, "latitude": 28.613, "longitude": 77.209 } becomes a LocationData
    @MessageMapping("/location/update")
    public void receiveLocation(LocationData data) {

        // Hand it off to the service which stores it in the ConcurrentHashMap
        // This is all this controller does — receive and forward
        // The actual storage and broadcasting logic lives in LocationService
        locationService.updateLocation(data);
    }
}
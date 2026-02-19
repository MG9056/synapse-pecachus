package com.example.control.synapse.service;

// It does two things:
// 1. Stores the latest location of every user in memory



import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.request.LocationData;
import com.example.control.synapse.dto.response.ClusteredPoint;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LocationService {

    // for Sos (added by shriharsh)

    private final Set<Long> activeSOSUsers = ConcurrentHashMap.newKeySet();

    // ConcurrentHashMap is used instead of a regular HashMap because multiple users
    // are sending location updates simultaneously from different threads
    // ConcurrentHashMap is thread-safe so updates don't corrupt each other
    // Key = userId, Value = their latest LocationData
    // Each time a user sends a new location, their entry is simply overwritten
    // So this map always holds the LATEST position of every active user
    private final ConcurrentHashMap<Long, LocationData> userLocations = new ConcurrentHashMap<>();

    // This is the same messagingTemplate you're already using for seat updates
    // It's used here to push the clustered heatmap data to the admin
    private final SimpMessagingTemplate messagingTemplate;

    public LocationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Called every time a user sends a location update via WebSocket
    // Simply puts their latest location into the map, overwriting the previous one
    // If userId 1001 sends a location, it replaces their old location — no duplicates
    public void updateLocation(LocationData data) {
        userLocations.put(data.getUserId(), data);


        // if locationData's user is in the sos list then broadcast their location immediately
        if (activeSOSUsers.contains(data.getUserId())) {

        messagingTemplate.convertAndSend(
            "/topic/admin/sos/" + data.getUserId(),
            data
        );
    }
    }

    // @Scheduled means Spring automatically calls this method every 2000ms (2 seconds)
    // No one has to trigger it — it runs on its own in the background continuously
    // This is what pushes live updates to the admin without the admin requesting them
    @Scheduled(fixedRate = 2000)
    public void broadcastHeatmap() {

        // If no users have sent their location yet, skip the broadcast
        // No point sending an empty list to the admin
        if (userLocations.isEmpty()) return;

        // Take all the raw locations in the map and cluster them
        List<ClusteredPoint> clustered = clusterLocations();

        // Push the clustered points to the admin's WebSocket topic
        // Anyone subscribed to this topic (i.e., the admin screen) receives this instantly
        messagingTemplate.convertAndSend("/topic/admin/heatmap", clustered);
    }

    // This method reduces thousands of raw GPS points into a smaller set of weighted clusters
    // Think of the stadium floor as a grid of tiny squares (buckets)
    // Every person gets assigned to their nearest square
    // The weight of each square = number of people standing in it
    private List<ClusteredPoint> clusterLocations() {

        // This map holds our grid buckets
        // Key = a string like "28.6139:77.2090" representing a grid cell
        // Value = the ClusteredPoint for that cell with a running weight count
        Map<String, ClusteredPoint> grid = new HashMap<>();

        // gridSize controls the size of each bucket
        // 0.0001 degrees is roughly 10 meters
        // So people within 10 meters of each other get grouped into the same cluster
        // You can make this larger (0.001 = ~100m) for coarser clustering or smaller for finer
        double gridSize = 0.0001;

        // Loop through every user's current location
        for (LocationData loc : userLocations.values()) {

            // Snap the user's exact latitude to the nearest grid line
            // e.g., 28.61394 becomes 28.6139, 28.61396 also becomes 28.6139
            // This is what groups nearby people together into the same bucket
            double bucketLat = Math.round(loc.getLatitude() / gridSize) * gridSize;
            double bucketLng = Math.round(loc.getLongitude() / gridSize) * gridSize;

            // Create a unique string key for this grid cell
            // Two users in the same 10m square will produce the same key
            String key = bucketLat + ":" + bucketLng;

            // compute() atomically checks if this bucket already exists
            // If it doesn't exist yet (existing == null): create a new ClusteredPoint with weight 1
            // If it already exists: just increment its weight by 1
            // This is how we count how many people are in each grid cell
            grid.compute(key, (k, existing) -> {
                if (existing == null) return new ClusteredPoint(bucketLat, bucketLng, 1);
                existing.setWeight(existing.getWeight() + 1);
                return existing;
            });
        }

        // Return all the grid cells as a list
        // Each cell has a lat, lng, and weight representing density
        // This is what gets serialized to JSON and sent to the admin
        return new ArrayList<>(grid.values());
    }

    public void startSOSTracking(Long userId) {
    activeSOSUsers.add(userId);
}

public void stopSOSTracking(Long userId) {
    activeSOSUsers.remove(userId);
}

}
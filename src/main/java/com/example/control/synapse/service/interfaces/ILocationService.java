package com.example.control.synapse.service.interfaces;

import com.example.control.synapse.dto.request.LocationData;

public interface ILocationService {
    void updateLocation(LocationData data);

    void broadcastHeatmap();
}

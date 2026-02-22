package com.example.control.synapse.dto.response;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDateTime datetime;
    private String category;
    private String description;
    
    // Flattened Stadium Data (Easier for Frontend)
    private String stadiumName;
    private String city;
    
    private Double minPrice; // The starting price we discussed
}


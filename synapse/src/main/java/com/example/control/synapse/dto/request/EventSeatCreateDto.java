package com.example.control.synapse.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSeatCreateDto {
    
    @NotNull(message = "Seat ID is required")
    private Long seatId;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    private Boolean availability = true;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
}
package com.example.control.synapse.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSeatResponseDto {
    
    private Long id;
    private Long seatId;
    private String row;
    private Integer seatNumber;
    private String seatCategory;
    private Long eventId;
    private String eventName;
    private Boolean availability;
    private Long bookingId;
    private Double price;
}
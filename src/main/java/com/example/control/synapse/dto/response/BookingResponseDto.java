package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {

    private Long id;
    private Long userId;
    private Long eventId;
    private Long stadiumId;
    private LocalDateTime bookingTime;
}

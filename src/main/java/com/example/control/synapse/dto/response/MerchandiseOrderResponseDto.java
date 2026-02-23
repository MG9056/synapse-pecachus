package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseOrderResponseDto {

    private Long id;
    private double price;
    private Long seatId;
    private Long userId;
    private Long stadiumId;
    private Long eventId;
    private LocalDateTime orderTime;
}

package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SosResponseDto {

    private Long id;
    private String alertType;
    private String message;
    private Boolean isActive;
    private LocalDateTime timeStamp;
    private Long userId;
    private Long eventId;
    private Long stadiumId;
}

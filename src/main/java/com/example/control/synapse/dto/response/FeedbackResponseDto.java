package com.example.control.synapse.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long eventId;
    private String eventName;
    private String content;
    private double rating;
    private LocalDateTime createdAt;
}


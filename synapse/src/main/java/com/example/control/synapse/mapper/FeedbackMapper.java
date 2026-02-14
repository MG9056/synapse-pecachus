package com.example.control.synapse.mapper;

import org.springframework.data.domain.Page;

import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;
import com.example.control.synapse.models.Feedback;

public class FeedbackMapper {
    public static FeedbackResponseDto toDTO(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        
        return FeedbackResponseDto.builder()
                .id(feedback.getId())
                .userId(feedback.getUser() != null ? feedback.getUser().getId() : null)
                .userName(feedback.getUser() != null ? feedback.getUser().getName() : null)
                .eventId(feedback.getEventId() != null ? feedback.getEventId().getId() : null)
                .eventName(feedback.getEventId() != null ? feedback.getEventId().getName() : null)
                .content(feedback.getContent())
                .rating(feedback.getRating())
                .build();
    }
    public static FeedbackPageResponseDto toPageDto(Page<Feedback> feedbackPages) {
        FeedbackPageResponseDto dto = new FeedbackPageResponseDto();
        dto.setData(feedbackPages.getContent().stream().map(feedback -> FeedbackMapper.toDTO(feedback)).toList());
        dto.setPage(feedbackPages.getNumber());
        dto.setSize(feedbackPages.getSize());
        dto.setTotalElements(feedbackPages.getTotalElements());
        dto.setTotalPages(feedbackPages.getTotalPages());
        dto.setLast(feedbackPages.isLast());
        return dto;
    }
}

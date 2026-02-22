package com.example.control.synapse.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;
import com.example.control.synapse.models.Feedback;

@Component  // ✅ Fixed: was missing @Component
public class FeedbackMapper {

    public static FeedbackResponseDto toDTO(Feedback feedback) {
        if (feedback == null) {
            return null;
        }

        return FeedbackResponseDto.builder()
                .id(feedback.getId())
                .userId(feedback.getUser() != null ? feedback.getUser().getId() : null)
                .userName(feedback.getUser() != null ? feedback.getUser().getUsername() : null)
                .eventId(feedback.getEvent() != null ? feedback.getEvent().getId() : null)
                .eventName(feedback.getEvent() != null ? feedback.getEvent().getName() : null)
                .content(feedback.getContent())
                .rating(feedback.getRating())
                .createdAt(feedback.getCreatedAt())
                .build();
    }

    public static FeedbackPageResponseDto toPageDto(Page<Feedback> feedbackPage) {
        return FeedbackPageResponseDto.builder()
                // ✅ Fixed: was dto.setData() which mapped to 'content' field — now consistent
                .data(feedbackPage.getContent().stream()
                        .map(FeedbackMapper::toDTO)
                        .toList())
                .page(feedbackPage.getNumber())
                .size(feedbackPage.getSize())
                .totalElements(feedbackPage.getTotalElements())
                .totalPages(feedbackPage.getTotalPages())
                .last(feedbackPage.isLast())
                .build();
    }
}

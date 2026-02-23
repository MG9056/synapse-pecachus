package com.example.control.synapse.service.interfaces;

import org.springframework.data.domain.Pageable;

import com.example.control.synapse.dto.request.FeedbackRequestDto;
import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;

public interface IFeedbackService {
    void createFeedback(FeedbackRequestDto request);

    FeedbackResponseDto getFeedbackById(Long id);

    FeedbackPageResponseDto getAllFeedback(Double minRating, Double maxRating, Pageable pageable);

    FeedbackPageResponseDto getAllFeedbackByUserId(Long userId, Double minRating, Double maxRating, Pageable pageable);

    FeedbackPageResponseDto getAllFeedbackByEventId(Long eventId, Double minRating, Double maxRating,
            Pageable pageable);
}

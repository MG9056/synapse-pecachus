package com.example.control.synapse.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.FeedbackRequestDto;
import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;
import com.example.control.synapse.service.FeedbackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService=feedbackService;
    }

    @PostMapping
    public Map<String,String> createFeedback(@Valid @RequestBody FeedbackRequestDto request) {
        return feedbackService.createFeedback(request.getUserId(),request.getEventId(),request.getContent(),request.getRating());
    }
    @GetMapping("/{id}")
    public FeedbackResponseDto getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }
    @GetMapping
    public FeedbackPageResponseDto getAllFeedback(
            Pageable pageable,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        
        return feedbackService.getAllFeedback(minRating, maxRating, pageable);
    }
    
    /**
     * Get all feedback by user ID with pagination and optional rating filters
     * GET /api/feedbacks/user/{userId}?page=0&size=10&minRating=4.0
     */
    @GetMapping("/user/{userId}")
    public FeedbackPageResponseDto getAllFeedbackByUserId(
            @PathVariable Long userId,
            Pageable pageable,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {   
        return feedbackService.getAllFeedbackByUserId(userId, minRating, maxRating, pageable);
    }
    
    /**
     * Get all feedback by event ID with pagination and optional rating filters
     * GET /api/feedbacks/event/{eventId}?page=0&size=10&maxRating=3.0
     */
    @GetMapping("/event/{eventId}")
    public FeedbackPageResponseDto getAllFeedbackByEventId(@PathVariable Long eventId,Pageable pageable,@RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        
        return feedbackService.getAllFeedbackByEventId(eventId, minRating, maxRating, pageable);

    }


}

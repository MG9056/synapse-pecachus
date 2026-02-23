package com.example.control.synapse.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import com.example.control.synapse.dto.response.MessageResponse;
import com.example.control.synapse.service.interfaces.IFeedbackService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final IFeedbackService feedbackService;

    /**
     * POST /feedback
     * Body: { "eventId", "content", "rating" }
     * userId is taken from the JWT — not from the request body
     */
    @PostMapping

    public ResponseEntity<MessageResponse> createFeedback(@Valid @RequestBody FeedbackRequestDto request) {
        // ✅ Fixed: no longer passing userId from body — service gets it from
        // SecurityContext
        feedbackService.createFeedback(request);
        return ResponseEntity.ok(new MessageResponse("Feedback submitted successfully!"));
    }

    /**
     * GET /feedback/{id}
     * Any authenticated user can view a specific feedback
     */
    @GetMapping("/{id}")

    public ResponseEntity<FeedbackResponseDto> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    /**
     * GET /feedback?minRating=&maxRating=
     * Admin only — view all feedback across the platform
     */
    @GetMapping

    public ResponseEntity<FeedbackPageResponseDto> getAllFeedback(
            Pageable pageable,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        return ResponseEntity.ok(feedbackService.getAllFeedback(minRating, maxRating, pageable));
    }

    /**
     * GET /feedback/user/{userId}?minRating=&maxRating=
     * Admin can view any user's feedback; users can only view their own
     */
    @GetMapping("/user/{userId}")

    public ResponseEntity<FeedbackPageResponseDto> getAllFeedbackByUserId(
            @PathVariable Long userId,
            Pageable pageable,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        return ResponseEntity.ok(feedbackService.getAllFeedbackByUserId(userId, minRating, maxRating, pageable));
    }

    /**
     * GET /feedback/event/{eventId}?minRating=&maxRating=
     * Any authenticated user can view feedback for an event
     */
    @GetMapping("/event/{eventId}")

    public ResponseEntity<FeedbackPageResponseDto> getAllFeedbackByEventId(
            @PathVariable Long eventId,
            Pageable pageable,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        return ResponseEntity.ok(feedbackService.getAllFeedbackByEventId(eventId, minRating, maxRating, pageable));
    }
}

package com.example.control.synapse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.control.synapse.dto.request.FeedbackRequestDto;
import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;
import com.example.control.synapse.mapper.FeedbackMapper;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.Feedback;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.FeedbackRepository;
import com.example.control.synapse.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.control.synapse.service.interfaces.IFeedbackService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService implements IFeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    /**
     * Create feedback — userId pulled from JWT, not request body
     */
    @Transactional
    public void createFeedback(FeedbackRequestDto request) {
        log.info("Creating feedback for event ID: {}", request.getEventId());

        // ✅ Fixed: userId now comes from the authenticated user's token
        User user = getCurrentUser();

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + request.getEventId()));

        Feedback feedback = new Feedback(user, event, request.getContent(), request.getRating());
        feedbackRepository.save(feedback);

        log.info("Feedback created successfully for event ID: {}", request.getEventId());
    }

    public FeedbackResponseDto getFeedbackById(Long id) {
        log.info("Fetching feedback with ID: {}", id);

        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));

        return FeedbackMapper.toDTO(feedback);
    }

    public FeedbackPageResponseDto getAllFeedback(Double minRating, Double maxRating, Pageable pageable) {
        log.info("Fetching all feedback — minRating: {}, maxRating: {}", minRating, maxRating);

        Page<Feedback> feedbackPage = applyRatingFilter(null, minRating, maxRating, pageable, "all");
        return FeedbackMapper.toPageDto(feedbackPage);
    }

    public FeedbackPageResponseDto getAllFeedbackByUserId(Long userId, Double minRating, Double maxRating,
            Pageable pageable) {
        log.info("Fetching feedback for user ID: {}", userId);

        // Validate user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Page<Feedback> feedbackPage = applyRatingFilter(userId, minRating, maxRating, pageable, "user");
        return FeedbackMapper.toPageDto(feedbackPage);
    }

    public FeedbackPageResponseDto getAllFeedbackByEventId(Long eventId, Double minRating, Double maxRating,
            Pageable pageable) {
        log.info("Fetching feedback for event ID: {}", eventId);

        // Validate event exists
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found with id: " + eventId);
        }

        Page<Feedback> feedbackPage = applyRatingFilter(eventId, minRating, maxRating, pageable, "event");
        return FeedbackMapper.toPageDto(feedbackPage);
    }

    // ─── Private helpers ─────────────────────────────────────────────────────

    /**
     * ✅ Fixed: partial rating filters (only min or only max) were silently ignored
     * before
     * Now handles all 4 combinations: both, minOnly, maxOnly, neither
     */
    private Page<Feedback> applyRatingFilter(Long entityId, Double minRating, Double maxRating,
            Pageable pageable, String type) {
        return switch (type) {
            case "user" -> {
                if (minRating != null && maxRating != null)
                    yield feedbackRepository.findByUser_IdAndRatingBetween(entityId, minRating, maxRating, pageable);
                else if (minRating != null)
                    yield feedbackRepository.findByUser_IdAndRatingGreaterThanEqual(entityId, minRating, pageable);
                else if (maxRating != null)
                    yield feedbackRepository.findByUser_IdAndRatingLessThanEqual(entityId, maxRating, pageable);
                else
                    yield feedbackRepository.findByUser_Id(entityId, pageable);
            }
            case "event" -> {
                if (minRating != null && maxRating != null)
                    yield feedbackRepository.findByEvent_IdAndRatingBetween(entityId, minRating, maxRating, pageable);
                else if (minRating != null)
                    yield feedbackRepository.findByEvent_IdAndRatingGreaterThanEqual(entityId, minRating, pageable);
                else if (maxRating != null)
                    yield feedbackRepository.findByEvent_IdAndRatingLessThanEqual(entityId, maxRating, pageable);
                else
                    yield feedbackRepository.findByEvent_Id(entityId, pageable);
            }
            default -> {
                if (minRating != null && maxRating != null)
                    yield feedbackRepository.findByRatingBetween(minRating, maxRating, pageable);
                else if (minRating != null)
                    yield feedbackRepository.findByRatingGreaterThanEqual(minRating, pageable);
                else if (maxRating != null)
                    yield feedbackRepository.findByRatingLessThanEqual(maxRating, pageable);
                else
                    yield feedbackRepository.findAll(pageable);
            }
        };
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new RuntimeException("No authenticated user found");
        }

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + authentication.getName()));
    }
}

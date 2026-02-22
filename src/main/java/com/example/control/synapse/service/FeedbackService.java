package com.example.control.synapse.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.control.synapse.dto.response.FeedbackPageResponseDto;
import com.example.control.synapse.dto.response.FeedbackResponseDto;
import com.example.control.synapse.mapper.FeedbackMapper;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.Feedback;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.EventRepository;
import com.example.control.synapse.repository.FeedbackRepository;
import com.example.control.synapse.repository.UserRepository;



@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, EventRepository eventRepository,
            UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Map<String,String> createFeedback(Long userId,Long eventId, String content,Double rating) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        Feedback feedback = new Feedback(user,event,content,rating);
        feedbackRepository.save(feedback);
        Map<String,String> response = new HashMap<>();
        response.put("message","Feedback stored successfully");
        return response;
        
    }
    public FeedbackResponseDto getFeedbackById(Long id) {
        
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
        
        return FeedbackMapper.toDTO(feedback);
    }

    public FeedbackPageResponseDto getAllFeedback(Double minRating, Double maxRating, Pageable pageable) {
        Page<Feedback> feedbackPage;
        
        // Apply rating filter if provided
        if (minRating != null && maxRating != null) {
            feedbackPage = feedbackRepository.findByRatingBetween(minRating, maxRating, pageable);
        } else {
            feedbackPage = feedbackRepository.findAll(pageable);
        }
        
        // Convert to DTO using mapper
        return FeedbackMapper.toPageDto(feedbackPage);
    }

    public FeedbackPageResponseDto getAllFeedbackByUserId(Long userId, Double minRating, Double maxRating, Pageable pageable) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Page<Feedback> feedbackPage;
        
        // Apply rating filter if provided
        if (minRating != null && maxRating != null) {
            feedbackPage = feedbackRepository.findByUserIdAndRatingBetween(user, minRating, maxRating, pageable);
        } else {
            feedbackPage = feedbackRepository.findByUserId(user, pageable);
        }
        
        // Convert to DTO using mapper
        return FeedbackMapper.toPageDto(feedbackPage);
    }
    public FeedbackPageResponseDto getAllFeedbackByEventId(Long eventId, Double minRating, Double maxRating, Pageable pageable) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        
        Page<Feedback> feedbackPage;
        
        // Apply rating filter if provided
        if (minRating != null && maxRating != null) {
            feedbackPage = feedbackRepository.findByEventIdAndRatingBetween(event, minRating, maxRating, pageable);
        } else {
            feedbackPage = feedbackRepository.findByEventId(event, pageable);
        }
        
        // Convert to DTO using mapper
        return FeedbackMapper.toPageDto(feedbackPage);
    }
    
}

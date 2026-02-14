package com.example.control.synapse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.Feedback;
import com.example.control.synapse.models.User;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    Page<Feedback> findByUserId(User user, Pageable pageable);
    
    Page<Feedback> findByEventId(Event event, Pageable pageable);
    
    // Methods with rating filters
    Page<Feedback> findByUserIdAndRatingBetween(User user, double minRating, double maxRating, Pageable pageable);
    
    Page<Feedback> findByEventIdAndRatingBetween(Event event, double minRating, double maxRating, Pageable pageable);
    
    Page<Feedback> findByRatingBetween(double minRating, double maxRating, Pageable pageable);
}

package com.example.control.synapse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.control.synapse.models.Feedback;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    
   Page<Feedback> findByUser_Id(Long userId, Pageable pageable);

    Page<Feedback> findByEvent_Id(Long eventId, Pageable pageable);

    Page<Feedback> findByUser_IdAndRatingBetween(Long userId, double minRating, double maxRating, Pageable pageable);

    Page<Feedback> findByEvent_IdAndRatingBetween(Long eventId, double minRating, double maxRating, Pageable pageable);

    Page<Feedback> findByRatingBetween(double minRating, double maxRating, Pageable pageable);

    // ✅ Added: handle partial rating filters (only min or only max provided)
    Page<Feedback> findByRatingGreaterThanEqual(double minRating, Pageable pageable);

    Page<Feedback> findByRatingLessThanEqual(double maxRating, Pageable pageable);

    Page<Feedback> findByUser_IdAndRatingGreaterThanEqual(Long userId, double minRating, Pageable pageable);

    Page<Feedback> findByUser_IdAndRatingLessThanEqual(Long userId, double maxRating, Pageable pageable);

    Page<Feedback> findByEvent_IdAndRatingGreaterThanEqual(Long eventId, double minRating, Pageable pageable);

    Page<Feedback> findByEvent_IdAndRatingLessThanEqual(Long eventId, double maxRating, Pageable pageable);

}

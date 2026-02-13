package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {



}

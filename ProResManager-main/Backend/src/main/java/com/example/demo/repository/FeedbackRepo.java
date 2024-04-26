package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long>{
    Feedback findByFeedbackId(Long feedbackId);
    Feedback findByComment(String comment);
    Feedback findByTask_TaskId(Long taskId);
    Feedback findByUser_UserId(Long userId);
    
}

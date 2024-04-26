package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Feedback;

@Component
public interface IFeedback {
    Feedback createFeedback(String comment, Long taskId, Long userId);
    Feedback updateFeedback(Long feedbackId,String comment);
    Feedback getFeedback(Long feedbackId);
    Integer removeFeedback(Long feedbackId);
    List<Feedback> getAllFeedbacks();
    
}

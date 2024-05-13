package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Feedback;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.repository.FeedbackRepo;
import com.example.demo.repository.TaskRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IFeedback;

@Service
public class FeedbackServices implements IFeedback {
    @Autowired
    FeedbackRepo feedbackRepository;
    @Autowired
    TaskRepo taskService;
    @Autowired
    UserRepo userService;

    @Override
    public Feedback createFeedback(String comment, Long taskId, Long userId) {
        Feedback feedback = new Feedback();
        feedback.setComment(comment);
    
        Task task = taskService.findByTaskId(taskId);
        feedback.setTask(task);
    
        User user = userService.findByUserId(userId);
        feedback.setUser(user);
    
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Long feedbackId, String comment)
    {  
        Feedback feedback = feedbackRepository.findByFeedbackId(feedbackId);
        feedback.setComment(comment);
        return feedbackRepository.save(feedback);

    }

    @Override
    public Feedback getFeedback(Long feedbackId) {
        return feedbackRepository.findByFeedbackId(feedbackId);
    }

    @Override
    public Integer removeFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
        return 1;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getFeedbacksByTask(Long taskId) {
        Task task = taskService.findByTaskId(taskId);
        return feedbackRepository.findByTask(task);
    }
    
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Feedback;
import com.example.demo.entities.Task;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long>{
    Feedback findByFeedbackId(Long feedbackId);
    Feedback findByComment(String comment);
    Feedback findByTask_TaskId(Long taskId);
    Feedback findByUser_UserId(Long userId);
    List<Feedback> findByTask(Task task);
    
}

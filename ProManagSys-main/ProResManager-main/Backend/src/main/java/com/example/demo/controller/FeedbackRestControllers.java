package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FeedbackDTO;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Feedback;
import com.example.demo.services.impl.FeedbackServices;

@RestController
@RequestMapping("/feedback")
public class FeedbackRestControllers {
    @Autowired
    FeedbackServices feedbackService;

    @PostMapping("/create")
    public ResponseEntity<?> createFeedback(@RequestParam String comment, 
                                                   @RequestParam Long taskId, 
                                                   @RequestParam Long userId) {
        Feedback feedback = feedbackService.createFeedback(comment, taskId, userId);
        ApiResponse response = new ApiResponse();
        if (feedback != null) {
            response.setMessage("Feedback created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Feedback creation failed");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{feedbackId}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long feedbackId, 
                                                   @RequestParam String comment) {
        Feedback feedback = feedbackService.updateFeedback(feedbackId, comment);
        ApiResponse response = new ApiResponse();
        if (feedback.getComment().equals(comment)) {
            response.setMessage("Feedback updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Feedback update failed");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyid/{feedbackId}")
    public ResponseEntity<?> getFeedback(@PathVariable Long feedbackId) {
        Feedback feedback = feedbackService.getFeedback(feedbackId);
        ApiResponse response = new ApiResponse();
        if (feedback != null) {
            FeedbackDTO feedbackDTO = convertToDTO(feedback);
            return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
        } else {
            response.setMessage("Feedback not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{feedbackId}")
    public ResponseEntity<?> removeFeedback(@PathVariable Long feedbackId) {
        Integer result = feedbackService.removeFeedback(feedbackId);
        ApiResponse response = new ApiResponse();
        if (result == 1) {
            response.setMessage("Feedback deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Feedback deletion failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
        for (Feedback feedback : feedbackList) {
            FeedbackDTO feedbackDTO = convertToDTO(feedback);
            feedbackDTOList.add(feedbackDTO);
        }
        return feedbackDTOList;
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getFeedbacksByTask(@PathVariable Long taskId) {
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        List<Feedback> feedbackList = feedbackService.getFeedbacksByTask(taskId);
        ApiResponse response = new ApiResponse();
        if(feedbackList.isEmpty()) {
            response.setMessage("No feedback found for this task");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Feedback feedback : feedbackList) {
            FeedbackDTO feedbackDTO = convertToDTO(feedback);
            feedbackDTOList.add(feedbackDTO);
        }
        return new ResponseEntity<>(feedbackDTOList, HttpStatus.OK);
    }
    
    public FeedbackDTO  convertToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setComment(feedback.getComment());
        return feedbackDTO;
    }
}

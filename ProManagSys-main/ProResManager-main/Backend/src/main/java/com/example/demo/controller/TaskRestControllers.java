package com.example.demo.controller;

import java.util.Date;
import java.util.Optional;

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

import com.example.demo.DTO.TaskDTO;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.services.impl.TaskService;

@RestController
@RequestMapping("/task")
public class TaskRestControllers { 
    @Autowired
    TaskService taskService;    

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestParam String taskName, @RequestParam String description, @RequestParam Date dueDate) {
        Task task = taskService.createTask(taskName, description, dueDate);
        ApiResponse response = new ApiResponse();
        if(task != null) {
            response.setMessage("Task created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Task creation failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/remove/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        ApiResponse response = new ApiResponse();
        if(taskService.getTaskById(taskId) == null) {
            response.setMessage("Task deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Task deletion failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateStatus/{taskId}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable("taskId") Long taskId, @RequestParam TaskStatus status) {
        taskService.updateTaskStatus(taskId, status);
        ApiResponse response = new ApiResponse();
        if(taskService.getTaskById(taskId).get().getStatus() == status) {
            response.setMessage("Task status updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Task status update failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable("taskId") Long taskId, @RequestParam String taskName, @RequestParam String description, @RequestParam Date dueDate, @RequestParam TaskStatus status) {
        taskService.updateTask(taskId, taskName, description, dueDate, status);
        ApiResponse response = new ApiResponse();
        if(taskService.getTaskById(taskId).get().getTaskName().equals(taskName) && taskService.getTaskById(taskId).get().getDescription().equals(description) && taskService.getTaskById(taskId).get().getDueDate().equals(dueDate) && taskService.getTaskById(taskId).get().getStatus().equals(status)) {
            response.setMessage("Task updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Task update failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyid/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable("taskId") Long taskId) {
        Optional<Task> task = Optional.ofNullable(taskService.getTaskById(taskId).get());
        ApiResponse response = new ApiResponse();
        if(taskService.getTaskById(taskId).isPresent()) {
            response.setMessage("Task found");
            TaskDTO taskDTO = convertToDTO(task.get());
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } else {
            response.setMessage("Task not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyname/{taskName}")
    public ResponseEntity<?> getTaskByName(@PathVariable("taskName") String taskName) {
        Optional<Task> task = Optional.ofNullable(taskService.getTaskByName(taskName).get());
        ApiResponse response = new ApiResponse();
        if(taskService.getTaskByName(taskName).isPresent()) {
            response.setMessage("Task found");
            TaskDTO taskDTO = convertToDTO(task.get());
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } else {
            response.setMessage("Task not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
    

    public TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }
}
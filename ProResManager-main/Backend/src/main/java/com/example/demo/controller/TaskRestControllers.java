package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.services.impl.TaskService;

@RestController
@RequestMapping("/task")
public class TaskRestControllers { 
    @Autowired
    TaskService taskService;    

    @PostMapping
    public Task createTask(@RequestParam String taskName, @RequestParam String description, @RequestParam Date dueDate) {
        return taskService.createTask(taskName, description, dueDate);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/{taskId}/status")
    public void updateTaskStatus(@PathVariable("taskId") Long taskId, @RequestParam TaskStatus status) {
        taskService.updateTaskStatus(taskId, status);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable("taskId") Long taskId, @RequestParam String taskName, @RequestParam String description, @RequestParam Date dueDate, @RequestParam TaskStatus status) {
        return taskService.updateTask(taskId, taskName, description, dueDate, status);
    }
}
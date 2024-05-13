package com.example.demo.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.repository.TaskRepo;
import com.example.demo.services.ITask;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService implements ITask {
    @Autowired
    TaskRepo taskRepository;

    @Override
    public Task createTask(String taskName, String description, Date dueDate) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(TaskStatus.NOT_STARTED);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Integer deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
        return 1;
        
    }

    @Override
    public Integer updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findByTaskId(taskId);
    if (task != null) {
        task.setStatus(status);
        taskRepository.save(task);
        return 1;
    } else {
        throw new EntityNotFoundException("Task with id " + taskId + " not found");
    }
}

    @Override
    public Task updateTask(Long taskId, String taskName, String description, Date dueDate, TaskStatus status) {
        Task task = taskRepository.findByTaskId(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Optional<Task> getTaskByName(String taskName) {
        return taskRepository.findByTaskName(taskName);
    }
    
}

package com.example.demo.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Project;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.entities.User;
import com.example.demo.repository.TaskRepo;
import com.example.demo.services.ITask;

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
        return task;
    }

    @Override
    public void deleteTask(Long taskId) {
        // Provide the implementation for deleting a task
        taskRepository.deleteById(taskId);
    }

    @Override
    public void updateTaskStatus(Long taskId, TaskStatus status) {
        // Provide the implementation for updating the task status
        Task task = taskRepository.findByTaskId(taskId);
        task.setStatus(status);
    }

    @Override
    public Task updateTask(Long taskId, String taskName, String description, Date dueDate, TaskStatus status) {
        Task task = taskRepository.findByTaskId(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        return task;
    }
}

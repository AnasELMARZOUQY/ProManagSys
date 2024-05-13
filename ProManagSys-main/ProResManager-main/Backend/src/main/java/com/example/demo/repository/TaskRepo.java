package com.example.demo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

    Optional<Task> findByTaskName(String taskName);

    Task findByDueDate(Date taskDueDate);

    Task findByStatus(TaskStatus taskStatus);

    Task findByTaskId(Long taskId);
    
}

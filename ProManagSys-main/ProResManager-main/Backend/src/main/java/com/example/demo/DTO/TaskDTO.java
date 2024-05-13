package com.example.demo.DTO;

import java.util.Date;

import com.example.demo.entities.TaskStatus;

import lombok.Data;
@Data
public class TaskDTO {
    private String taskName;
    private String description;
    private Date dueDate;
    private TaskStatus status;
}

package com.example.demo.DTO;

import java.util.Date;

import com.example.demo.entities.ProjectStatus;
import com.example.demo.entities.User;

import lombok.Data;
@Data
public class ProjectDTO {
    private String projectName;
    private String description;
    private Date deadline;
    private ProjectStatus status;
    private User projectManager;
}

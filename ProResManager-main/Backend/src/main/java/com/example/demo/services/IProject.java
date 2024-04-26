package com.example.demo.services;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public interface IProject {
    Project updateProject(Project p,Long projectId);

    Optional<Project> getProjectWithID(Long id);
    List<Project> getAllProject();

    Integer removeProject(Long idprojet, Long idUser);

    Project createProject(String projectName, String descriptionm, Date deadline);
    Project updateStatus(ProjectStatus status, Long projectId);



}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectStatus;

@Repository
@EnableJpaRepositories
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Project findByProjectName(String nomProjet);
    Project findByStatus(ProjectStatus status);
}
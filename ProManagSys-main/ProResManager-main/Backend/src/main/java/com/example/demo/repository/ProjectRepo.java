package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectStatus;

@Repository
@EnableJpaRepositories
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectName(String nomProjet);
    Project findByStatus(ProjectStatus status);
}
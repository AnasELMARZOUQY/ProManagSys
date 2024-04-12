package com.example.demo.repository;

import com.example.demo.entites.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface projetRepo extends JpaRepository<Project, Long> {

    Project findByNomprojet(String nomProjet);
}
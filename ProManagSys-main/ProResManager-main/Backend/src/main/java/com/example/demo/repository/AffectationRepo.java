package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Affectation;
import com.example.demo.entities.Project;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;

import java.util.List;

@Repository
public interface AffectationRepo extends JpaRepository<Affectation, Long> {
    Affectation findByProject(Project project);
    Affectation findByUser(User user);
    Affectation findByTask(Task task);
    List<Affectation> findByUser_UserId(Long userId);;
    List<Affectation> findByTask_TaskId(Long taskId);
    List<Affectation> findByProject_ProjectId(Long projectId);
    List<Affectation> findByUser_UserName(String userName);
    List<Affectation> findByTask_TaskName(String taskName);
    List<Affectation> findByProject_ProjectName(String projectName);
    
    //Affectation findByResssource(Ressources ressource);

    // List<Affectation> findByNomRessourceAndPrenomRessource(String nomR, String prenomR);

    // Affectation findByProjetAndResssource(String projet, Ressources ressource);
}
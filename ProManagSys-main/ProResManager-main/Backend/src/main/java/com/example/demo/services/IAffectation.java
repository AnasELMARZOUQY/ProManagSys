package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Affectation;
import com.example.demo.entities.Project;
import com.example.demo.entities.Resource;
import com.example.demo.entities.Task;

import java.util.List;
import java.util.Optional;

@Component
public interface IAffectation {

    List<Affectation> getAllAffectations();
    
    Optional<Affectation> getAffectationWithID(Long id);
    List<Affectation> getAffectationsByUserId(Long userId);
    List<Affectation> getAffectationsByTaskId(Long taskId);
    List<Affectation> getAffectationsByProjectId(Long projectId);

    List<Affectation>getAffectationsByUserName(String userName);
    List<Affectation>getAffectationsByTaskName(String taskName);
    List<Affectation>getAffectationsByProjectName(String projectName);
    // affect a task to a user
    Affectation assignTasktoUser(String taskName, String userName);
    Affectation assignProjecttoUser(String projectName, String userName);
    Affectation assignResourceToUser(String resourceName, String userName);
    Affectation changeAffectation(Long id, String newUserName);

    // List<String> addAffectation(Affectation affectation , String idUser);


    // List<String> getExceededMonths(Affectation newAffectation, Ressources resource);
    // Affectation updateAffectation(Affectation affectation, Long idAffectation);
    void deleteAffectation(Long idAffectation);
}

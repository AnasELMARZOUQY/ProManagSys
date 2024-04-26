package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Affectation;
import com.example.demo.entities.Project;
import com.example.demo.entities.Resource;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Component
public interface IAffectation {

    List<Affectation> getAllAffectations();
    Optional<Affectation> getAffectationWithID(Long id);
    List<Affectation> getAffectationsByUserId(Long userId);
    List<Affectation> getAffectationsByTaskId(Long taskId);
    List<Affectation> getAffectationsByProjectId(Long projectId);
    // affect a task to a user
    Affectation assignTasktoUser(Task task, User user);
    Affectation assignProjecttoUser(Project project, User user);
    Affectation assignResourceToUser(Resource resource, User user);
    Affectation changeAffectation(Long id, User newuser);

    // List<String> addAffectation(Affectation affectation , String idUser);


    // List<String> getExceededMonths(Affectation newAffectation, Ressources resource);
    // Affectation updateAffectation(Affectation affectation, Long idAffectation);
    void deleteAffectation(Long idAffectation);
}

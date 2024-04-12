package com.example.demo.services;
import com.example.demo.entites.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IProjet {
    Project updateProjet(Project p,Long projectId);

    Optional<Project> getProjetWithID(Long id);
    List<Project> GetAllprojet();

    Integer removeProjet(Long idprojet,  String idUser);

    Project addProjetwithIdUser(Project p, String idUser);



}

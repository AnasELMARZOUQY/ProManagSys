package com.example.demo.services.impl;

import com.example.demo.entities.*;
import com.example.demo.repository.ProjectRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IProject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@Slf4j
public class ProjectServices implements IProject {
    @Autowired
    ProjectRepo projetRepository;

    @Autowired
    UserRepo userRepository;


    @Override
    public List<Project> getAllProject() {
        return projetRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectWithID(Long id){
        return projetRepository.findById(id);
    }


    public static List<String> getProjectAttributes(Class<?> ProjectClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = ProjectClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    @Override
    public Project updateProject(Project p,Long projectId) {
        Project existingProjet = projetRepository.findById(projectId).orElse(null);
        List<String> attributes = getProjectAttributes(Project.class);
        Set<String> ignoreProperties = new HashSet<>();
        for (String attribute : attributes) {
            if (p.get(attribute) == null)
                ignoreProperties.add(attribute);
        }
        String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
        BeanUtils.copyProperties(p, existingProjet, ignorePropertiesArray);
        return projetRepository.save(existingProjet);
    }

    /*@Override
    public Integer removeProjet(Long idprojet, String idUser) {
        User currentUser = userRepository.findById(idUser).orElse(null);
        if ((currentUser.getRole().getRoleName() != "Admin") && (currentUser.getRole().getRoleName() == "Manager")){
            projetRepository.deleteById(idprojet);
            return 1;
        }
        else
            return 0;
    }*/

    @Override
    public Integer removeProject(Long idprojet, Long idUser){
        User currentUser = userRepository.findById(idUser).orElse(null);
        if (currentUser != null && currentUser.getRole() != null) {
            String roleName = currentUser.getRole().getRoleName();
            if ("Admin".equals(roleName)) {
                projetRepository.deleteById(idprojet);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Project createProject(String projectName, String description, Date deadline) {
        Project project = new Project();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCreatedAt(new Date());
        project.setStatus(ProjectStatus.NOT_STARTED);
        project.setDeadline(deadline);

        // Set any other properties of the project as needed
        return projetRepository.save(project);
    }

    @Override
    public Project updateStatus(ProjectStatus status, Long projectId) {
        Project project = projetRepository.findById(projectId).orElse(null);
        project.setStatus(status);
        return projetRepository.save(project);
    }

}

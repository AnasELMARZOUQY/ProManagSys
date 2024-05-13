package com.example.demo.controller;

import com.example.demo.DTO.ProjectDTO;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectStatus;
import com.example.demo.services.impl.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/project")
public class ProjetRestControllers {
    @Autowired
    ProjectServices projetControl;


    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    List<ProjectDTO> GetAllprojet() {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        List<Project> projectList = projetControl.getAllProject();
        for (Project project : projectList) {
            ProjectDTO projectDTO = convertToDTO(project);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

   @GetMapping("/getbyid/{idProjet}")
   public ResponseEntity<?> getProjetById(@PathVariable("idProjet") Long idProjet) {
        Optional<Project> optionalProjet = projetControl.getProjectWithID(idProjet);
        ApiResponse response = new ApiResponse();
        if (optionalProjet.isPresent()){
            ProjectDTO projectDTO = convertToDTO(optionalProjet.get());
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        }
        else {
            response.setMessage("projet not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
   }
   @GetMapping("/getbyname/{projetName}")
    public ResponseEntity<?> getProjetByName(@PathVariable("projetName") String projetName) {
          Optional<Project> optionalProjet = projetControl.getProjectWithName(projetName);
          ApiResponse response = new ApiResponse();
          if (optionalProjet.isPresent()){
            ProjectDTO projectDTO = convertToDTO(optionalProjet.get());
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
          }
          else {
                response.setMessage("projet not found");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
          }
    }
    
    @PutMapping("/update/{idprojet}")
    public ResponseEntity<?> updateProjet(@RequestBody Project p,
                             @PathVariable("idprojet") Long idprojet) {
        projetControl.updateProject(p,idprojet);
        ApiResponse response = new ApiResponse();
        if(projetControl.updateProject(p,idprojet) != null) {
            response.setMessage("Project updated successfully !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Project update failed !");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/remove/{iduser}/{idprojet}")
    public ResponseEntity<?> removeprojet(@PathVariable("idprojet") Long idprojet,
                             @PathVariable("iduser") Long idUser) {
        Integer test = projetControl.removeProject(idprojet, idUser);
        ApiResponse response = new ApiResponse();
        if (test == 1) {
            response.setMessage("Project deleted !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a project");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestParam String projectName, 
                                                 @RequestParam String description, 
                                                 @RequestParam Date deadline) {
        Project project = projetControl.createProject(projectName, description, deadline);
        ApiResponse response = new ApiResponse();
        if(project != null) {
            response.setMessage("Project created !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Project already exists !");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateStatus/{projectId}")
    public ResponseEntity<Project> updateStatus(@RequestParam ProjectStatus status, 
                                                @PathVariable Long projectId) {
        Project project = projetControl.updateStatus(status, projectId);
        ApiResponse response = new ApiResponse();
        if (project.getStatus() == status) {
            response.setMessage("Project status updated !");
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            response.setMessage("Project status cannot be updated !");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setDeadline(project.getDeadline());
        projectDTO.setStatus(project.getStatus());
        return projectDTO;
    }

}

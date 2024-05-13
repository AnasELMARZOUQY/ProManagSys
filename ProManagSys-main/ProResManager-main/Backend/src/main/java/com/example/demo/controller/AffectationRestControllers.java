package com.example.demo.controller;

import com.example.demo.DTO.AffectationDTO;
import com.example.demo.entities.Affectation;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Project;
import com.example.demo.entities.Resource;
import com.example.demo.entities.Task;
import com.example.demo.services.impl.AffectationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/affectation")
public class AffectationRestControllers {
    @Autowired
    AffectationServices affectationControl;

    @GetMapping("/all")  
    List<AffectationDTO> getAllAffectations() {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectationList = affectationControl.getAllAffectations();
        for (Affectation affectation : affectationList) {
            AffectationDTO feedbackDTO = convertToDTO(affectation);
            affectationDTOList.add(feedbackDTO);
        }
        return affectationDTOList;
    }

    @GetMapping("/getbyid/{idAffectation}")
    public ResponseEntity<?> getAffectationWithId(@PathVariable("idAffectation") Long idAffectation) {
        Optional<Affectation> optionalAffectation = affectationControl.getAffectationWithID(idAffectation);
        ApiResponse response = new ApiResponse();
        if (optionalAffectation.isPresent()){
            AffectationDTO affectationDTO = convertToDTO(optionalAffectation.get());
            return new ResponseEntity<>(affectationDTO, HttpStatus.OK);
        }
        else {
            response.setMessage("affectation not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/remove/{idAffectation}")
    public ResponseEntity<?> deleteAffectation(@PathVariable("idAffectation") Long idAffectation) {
        affectationControl.deleteAffectation(idAffectation);
        ApiResponse response = new ApiResponse();
        if(affectationControl.getAffectationWithID(idAffectation).isPresent()){
            response.setMessage("affectation deletion failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        else {
            response.setMessage("affectation deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/getbyid/user/{userId}")
    public ResponseEntity<?> getAffectationsByUserId(@PathVariable("userId") Long userId) {
        Optional<Affectation> optionalAffectation = affectationControl.getAffectationWithID(userId);
        ApiResponse response = new ApiResponse();
        if(optionalAffectation.isPresent()){
            AffectationDTO affectationDTO = convertToDTO(optionalAffectation.get());
            return new ResponseEntity<>(affectationDTO, HttpStatus.OK);
        }
        else {
            response.setMessage("affectation not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyid/task/{taskId}")
    public ResponseEntity<?> getAffectationsByTaskId(@PathVariable("taskId") Long taskId) {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectationList = affectationControl.getAffectationsByTaskId(taskId);
        ApiResponse response = new ApiResponse();
        if(affectationList.isEmpty()) {
            response.setMessage("No affectation found for this task");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Affectation affectation : affectationList) {
            AffectationDTO affectationDTO = convertToDTO(affectation);
            affectationDTOList.add(affectationDTO);
        }
        return new ResponseEntity<>(affectationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getbyid/project/{projectId}")
    public ResponseEntity<?> getAffectationsByProjectId(@PathVariable("projectId") Long projectId) {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectationList = affectationControl.getAffectationsByProjectId(projectId);
        ApiResponse response = new ApiResponse();
        if(affectationList.isEmpty()) {
            response.setMessage("No affectation found for this project");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Affectation affectation : affectationList) {
            AffectationDTO affectationDTO = convertToDTO(affectation);
            affectationDTOList.add(affectationDTO);
        }
        return new ResponseEntity<>(affectationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getbyname/user/{userName}")
    public ResponseEntity<?> getAffectationsByUserName(@PathVariable String userName) {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectationList = affectationControl.getAffectationsByUserName(userName);
        ApiResponse response = new ApiResponse();
        if(affectationList.isEmpty()) {
            response.setMessage("No affectation found for this user");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Affectation affectation : affectationList) {
            AffectationDTO affectationDTO = convertToDTO(affectation);
            affectationDTOList.add(affectationDTO);
        }
        return new ResponseEntity<>(affectationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getbyname/task/{taskName}")
    public ResponseEntity<?> getAffectationsByTaskName(@PathVariable String taskName) {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectationList = affectationControl.getAffectationsByTaskName(taskName);
        ApiResponse response = new ApiResponse();
        if(affectationList.isEmpty()) {
            response.setMessage("No affectation found for this task");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Affectation affectation : affectationList) {
            AffectationDTO affectationDTO = convertToDTO(affectation);
            affectationDTOList.add(affectationDTO);
        }
        return new ResponseEntity<>(affectationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getbyname/project/{projectName}")
    public ResponseEntity<?> getAffectationsByProjectName(@PathVariable String projectName) {
        List<AffectationDTO> affectationDTOList = new ArrayList<>();
        List<Affectation> affectations = affectationControl.getAffectationsByProjectName(projectName);
        ApiResponse response = new ApiResponse();
        if(affectations.isEmpty()) {
            response.setMessage("No affectation found for this project");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        for (Affectation affectation : affectations) {
            AffectationDTO affectationDTO = convertToDTO(affectation);
            affectationDTOList.add(affectationDTO);
        }
        return new ResponseEntity<>(affectationDTOList, HttpStatus.OK);
    }

    @PostMapping("/assigntask/task")
    public ResponseEntity<?> assignTasktoUser(@RequestParam String taskName, @RequestParam String userName) {
        Affectation affectation = affectationControl.assignTasktoUser(taskName, userName);
        ApiResponse response = new ApiResponse();
        if (affectation != null) {
            response.setMessage("Task assigned to user successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Task assignment failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/assignprojet/project")
    public ResponseEntity<?> assignProjecttoUser(@RequestParam String projectName, @RequestParam String userName) {
        Affectation affectation = affectationControl.assignProjecttoUser(projectName, userName);
        ApiResponse response = new ApiResponse();
        if (affectation != null) {
            response.setMessage("Project assigned to user successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Project assignment failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/assignresource/resource")
    public ResponseEntity<?> assignResourceToUser(@RequestParam String resourceName, @RequestParam String userName) {
        Affectation affectation = affectationControl.assignResourceToUser(resourceName, userName);
        ApiResponse response = new ApiResponse();
        if (affectation != null) {
            response.setMessage("Resource assigned to user successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Resource assignment failed");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<?> changeAffectation(@PathVariable Long id, @RequestParam String newUserName) {
        Affectation affectation = affectationControl.changeAffectation(id, newUserName);
        ApiResponse response = new ApiResponse();
        if (affectation != null) {
            response.setMessage("Affectation updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Affectation update failed");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public AffectationDTO convertToDTO(Affectation affectation) {
        AffectationDTO affectationDTO = new AffectationDTO();
        if(affectationDTO.getUserName().isEmpty()){
            affectationDTO.setUserName("null");}
        else{
            affectationDTO.setUserName(affectation.getUser().get().getUserName());}

        if(affectationDTO.getTaskName().isEmpty()){
            affectationDTO.setTaskName("null");}
        else{        affectationDTO.setTaskName(affectation.getTask().get().getTaskName());}

        if(affectationDTO.getProjectName().isEmpty()){
            affectationDTO.setProjectName("null");}
        else{        affectationDTO.setProjectName(affectation.getProject().get().getProjectName());}
        
        return affectationDTO;
    }

}





  


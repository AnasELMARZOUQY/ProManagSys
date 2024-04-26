package com.example.demo.controller;

import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Project;
import com.example.demo.services.impl.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/project")
public class ProjetRestControllers {
    @Autowired
    ProjectServices  projetControl;


    @GetMapping("/GetallProject")
    @PreAuthorize("isAuthenticated()")
    List<Project> GetAllprojet() {
             return projetControl.getAllProject();
    }

   @GetMapping("/GetProjet/{idProjet}")
   public ResponseEntity<Project> getProjetById(@PathVariable("idProjet") Long idProjet) {
        Optional<Project> optionalProjet = projetControl.getProjectWithID(idProjet);
        ApiResponse response = new ApiResponse();
        if (optionalProjet.isPresent())
            return ResponseEntity.ok(optionalProjet.get());
        else {
            response.setMessage("projet not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
   }

//    @PostMapping("/addprojet/{idUser}")
//    public ResponseEntity<String> addProjetwithIdUser(@RequestBody Project p ,
//                                                           @PathVariable("idUser") String idUser) {
//                                                             Project projets = projetControl.addProjetwithIdUser(p, idUser);
//         ApiResponse response = new ApiResponse();
//         if (projets != null) {
//             response.setMessage("Projet ajouté avec succès !");
//             return new ResponseEntity(response , HttpStatus.OK);
//         } else {
//             response.setMessage("Projet existe déjà");
//             return new ResponseEntity(response , HttpStatus.CONFLICT);

//         }
//     }

    //host:8082/updateProjet/1
    @PutMapping("/updates/{idprojet}")
    public ResponseEntity<String> updateProjet(@RequestBody Project p,
                             @PathVariable("idprojet") Long idprojet) {
        projetControl.updateProject(p,idprojet);
        ApiResponse response = new ApiResponse();
        response.setMessage("Project updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/removeProjet/{iduser}/{idprojet}")
    public ResponseEntity<String> removeprojet(@PathVariable("idprojet") Long idprojet,
                             @PathVariable("iduser") Long idUser) {
        Integer test = projetControl.removeProject(idprojet, idUser);
        ApiResponse response = new ApiResponse();
        if (test == 1) {
            response.setMessage("Project deleted !");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a project");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

}

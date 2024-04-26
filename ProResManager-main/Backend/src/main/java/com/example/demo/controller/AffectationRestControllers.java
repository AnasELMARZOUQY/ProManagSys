package com.example.demo.controller;

import com.example.demo.entities.Affectation;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Project;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.services.impl.AffectationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/affectation")
public class AffectationRestControllers {
    @Autowired
    AffectationServices affectationControl;

    @GetMapping("/all")  //check
    public ResponseEntity<List<Affectation>> getAllAffectations() {
        return ResponseEntity.ok(affectationControl.getAllAffectations());
    }

    @GetMapping("/{idAffectation}")
    public ResponseEntity<Optional<Affectation>> getAffectationWithId(@PathVariable("idAffectation") Long idAffectation) {
        // Optional<Affectation> optionalAffectation = affectationControl.getAffectationWithID(idAffectation);
        // ApiResponse response = new ApiResponse();
        // if (optionalAffectation.isPresent())
        //     return ResponseEntity.ok(optionalAffectation.get());
        // else {
        //     response.setMessage("affectation not found");
        //     return new ResponseEntity(response, HttpStatus.CONFLICT);
        // }
        return ResponseEntity.ok(affectationControl.getAffectationWithID(idAffectation));
    }

    @DeleteMapping("{idAffectation}")
    public ResponseEntity<Void> deleteAffectation(@PathVariable("idAffectation") Long idAffectation) {
        // affectationControl.deleteAffectation(idAffectation);
        // ApiResponse response = new ApiResponse();
        // response.setMessage("Project deleted !");
        // return new ResponseEntity(response, HttpStatus.OK);
        affectationControl.deleteAffectation(idAffectation);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Affectation>> getAffectationsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(affectationControl.getAffectationsByUserId(userId));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Affectation>> getAffectationsByTaskId(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(affectationControl.getAffectationsByTaskId(taskId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Affectation>> getAffectationsByProjectId(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(affectationControl.getAffectationsByProjectId(projectId));
    }

    @PostMapping("/assign/task")
    public ResponseEntity<Affectation> assignTasktoUser(@RequestBody Task task, @RequestBody User user) {
        return ResponseEntity.ok(affectationControl.assignTasktoUser(task, user));
    }

    @PostMapping("/assign/project")
    public ResponseEntity<Affectation> assignProjecttoUser(@RequestBody Project project, @RequestBody User user) {
        return ResponseEntity.ok(affectationControl.assignProjecttoUser(project, user));
    }


    // @PostMapping("/addaffectation/{idUser}")
    // public ResponseEntity<String> addAffectation(@RequestBody Affectation Affectation,
    //                                              @PathVariable("idUser") String idUser) {
    //     List<String> affectation = affectationControl.addAffectation(Affectation,idUser);
    //     ApiResponse response = new ApiResponse();

    //     if(affectation == null){
    //         response.setMessage("une affectation avec le meme projet et meme ressource existe déjà");
    //         return new ResponseEntity(response, HttpStatus.CONFLICT);

    //     }
    //     else if(affectation.isEmpty()){
    //         response.setMessage("affectation ajouté avec succès !");
    //         return new ResponseEntity(response, HttpStatus.OK);

    //     }
    //     else{
    //         response.setMessage("vous avez depassé les nombre de jours de travail de ces mois" + affectation);
    //         return new ResponseEntity(response, HttpStatus.CONFLICT);

    //     }
    // }





    // @PutMapping("/update/{idAffectation}")
    // public ResponseEntity<String> updateAffectation(@RequestBody Affectation affectation,
    //                               @PathVariable("idAffectation") Long idAffectation){
    //     affectationControl.updateAffectation(affectation, idAffectation);
    //     ApiResponse response = new ApiResponse();
    //     response.setMessage("Project updated successfully !");
    //     return new ResponseEntity(response, HttpStatus.OK);
    // }




  
}

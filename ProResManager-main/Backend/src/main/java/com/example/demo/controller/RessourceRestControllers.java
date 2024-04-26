package com.example.demo.controller;

import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Resource;
import com.example.demo.services.impl.RessourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resource")
public class RessourceRestControllers {

    @Autowired
    RessourceServices ressourceControl;


    @GetMapping("/getallResource")
    List<Resource> getAllResource(){
        return ressourceControl.getAllResources();
    }

    @GetMapping("/GetRessource/{idRessource}")
    public ResponseEntity<Resource> getProjetById(@PathVariable("idRessource") Long idRessource) {
        Optional<Resource> optionalRessource = ressourceControl.getResourceWithID(idRessource);
        ApiResponse response = new ApiResponse();
        if (optionalRessource.isPresent())
            return ResponseEntity.ok(optionalRessource.get());
        else {
            response.setMessage("ressource not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/GetRessourceByName/{ressourceName}")
    public ResponseEntity<Resource> getProjetByName(@PathVariable("ressourceName") String ressourceName) {
        Resource ressource = ressourceControl.getResourceWithName(ressourceName);
        ApiResponse response = new ApiResponse();
        if (ressource != null)
            return ResponseEntity.ok(ressource);
        else {
            response.setMessage("ressource not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{idRessource}")
    public ResponseEntity<String> updateRessource(@RequestBody Resource r,
                                @PathVariable("idRessource") Long idRessource ) {
        ressourceControl.updateResource(r,idRessource);
        ApiResponse response = new ApiResponse();
        response.setMessage("Ressource updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/removeRessource/{iduser}/{idressource}")
    public ResponseEntity<String> removeRessource(@PathVariable("idressource") Long idressource,
                             @PathVariable("iduser") Long idUser) {
        ApiResponse response = new ApiResponse();
        Integer test = ressourceControl.removeResource(idressource, idUser);
        if(test == 1){
            response.setMessage("ressource deleted !");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a resource");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

}

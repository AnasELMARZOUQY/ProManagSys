package com.example.demo.controller;

import com.example.demo.DTO.ResourceDTO;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Resource;
import com.example.demo.services.impl.RessourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resource")
public class RessourceRestControllers {

    @Autowired
    RessourceServices ressourceControl;


    @GetMapping("/all")
    List<ResourceDTO> getAllResource(){
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        List<Resource> resourceList = ressourceControl.getAllResources();
        for (Resource resource : resourceList) {
            ResourceDTO resourceDTO = convertToDTO(resource);
            resourceDTOList.add(resourceDTO);
        }
        return resourceDTOList;
    }

    @GetMapping("/getbyid/{idRessource}")
    public ResponseEntity<?> getProjetById(@PathVariable("idRessource") Long idRessource) {
        Optional<Resource> optionalRessource = ressourceControl.getResourceWithID(idRessource);
        ApiResponse response = new ApiResponse();
        if (optionalRessource.isPresent()){
            ResourceDTO resourceDTO = convertToDTO(optionalRessource.get());
            return new ResponseEntity<>(resourceDTO, HttpStatus.OK);
        }
        else {
            response.setMessage("ressource not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getbyname/{ressourceName}")
    public ResponseEntity<?> getProjetByName(@PathVariable("ressourceName") String ressourceName) {
        Optional<Resource> ressource = ressourceControl.getResourceWithName(ressourceName);
        ApiResponse response = new ApiResponse();
        if (ressource != null){
            ResourceDTO resourceDTO = convertToDTO(ressource.get());
            return new ResponseEntity<>(resourceDTO, HttpStatus.OK);
        }
        else {
            response.setMessage("ressource not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createResource(@RequestParam String resourceName, 
                                                   @RequestParam Integer quantity, 
                                                   @RequestParam String type, 
                                                   @RequestParam Double unitPrice) {
        Optional<Resource> resource = ressourceControl.createResource(resourceName, quantity, type, unitPrice);
        ApiResponse response = new ApiResponse();
        if (resource.isPresent()) {
            response.setMessage("Resource created !");
            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } else {
            response.setMessage("Resource already exists !");
            return new ResponseEntity<>(resource, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{idRessource}")
    public ResponseEntity<?> updateResource(@PathVariable Long idRessource, 
                                                   @RequestParam String resourceName, 
                                                   @RequestParam Integer quantity, 
                                                   @RequestParam String type, 
                                                   @RequestParam Double unitPrice) {
        Integer resource = ressourceControl.updateResource(resourceName, quantity, type, unitPrice, idRessource);
        ApiResponse response = new ApiResponse();

        if (resource != null) {
            response.setMessage("Resource updated !");
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            response.setMessage("Resource cannot be updated !");
            return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/remove/{iduser}/{idressource}")
    public ResponseEntity<?> removeRessource(@PathVariable("idressource") Long idressource,
                             @PathVariable("iduser") Long idUser) {
        ApiResponse response = new ApiResponse();
        Integer test = ressourceControl.removeResource(idressource, idUser);
        if(test == 1){
            response.setMessage("ressource deleted !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a resource");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    public ResourceDTO convertToDTO(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResourceName(resource.getResourceName());
        resourceDTO.setQuantity(resource.getQuantity());
        resourceDTO.setType(resource.getType());
        resourceDTO.setUnitPrice(resource.getUnitPrice());
        return resourceDTO;
    }
}

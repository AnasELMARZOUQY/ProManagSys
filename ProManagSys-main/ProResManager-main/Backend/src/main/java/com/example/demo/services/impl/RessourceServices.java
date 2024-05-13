package com.example.demo.services.impl;

import com.example.demo.entities.*;
import com.example.demo.repository.ResourceRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.reflect.Field;
@Service
public class RessourceServices implements IResource {
    @Autowired
    ResourceRepo ressourceRepository;
    @Autowired
    UserRepo userRepository;

    @Override
    public List<Resource> getAllResources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Optional<Resource> getResourceWithID(Long id){
        return ressourceRepository.findById(id);
    }

    @Override
    public Integer removeResource(Long idRessources, Long idUser) {
        User currentUser = userRepository.findById(idUser).orElse(null);
        if (currentUser != null && currentUser.getRole() != null) {
            String roleName = currentUser.getRole().getRoleName();
            if ("Admin".equals(roleName)) {
                ressourceRepository.deleteById(idRessources);
                return 1;
            }
        }
        return 0;
    }

    public static List<String> getResourceAttributes(Class<?> resourceClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = resourceClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    @Override
    public Integer updateResource(String resourceName, Integer quantity, String type, Double unitPrice, Long idRessource) {
        Resource existingRessource = ressourceRepository.findById(idRessource).orElse(null);
        if (existingRessource != null) {
            existingRessource.setResourceName(resourceName);
            existingRessource.setQuantity(quantity);
            existingRessource.setType(type);
            existingRessource.setUnitPrice(unitPrice);
            existingRessource.setTotalCost();
            ressourceRepository.save(existingRessource);
        }
        return 1;
    }

    @Override
    public Optional<Resource> createResource(String resourceName, Integer quantity, String type, Double unitPrice) {
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setQuantity(quantity);
        resource.setType(type);
        resource.setUnitPrice(unitPrice);
        resource.setTotalCost();
        ressourceRepository.save(resource);
        return Optional.of(resource);
    }

    @Override
    public Optional<Resource> getResourceWithName(String resourceName) {
        return ressourceRepository.findByResourceName(resourceName);
    }
}

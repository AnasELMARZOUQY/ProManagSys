package com.example.demo.services.impl;

import com.example.demo.entities.*;
import com.example.demo.repository.ResourceRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.reflect.Field;
@Service
@Slf4j
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
    public Resource updateResource(Resource r, Long idRessource) {
        Resource existingRessource = ressourceRepository.findById(idRessource).orElse(null);
        List<String> attributes = getResourceAttributes(Resource.class);
        Set<String> ignoreProperties = new HashSet<>();
        for (String attribute : attributes) {
            if (r.get(attribute) == null)
                ignoreProperties.add(attribute);
        }
        String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
        BeanUtils.copyProperties(r, existingRessource, ignorePropertiesArray);
        return ressourceRepository.save(existingRessource);
    }

    @Override
    public Resource createResource(String resourceName, Integer quantity, String type, Double unitPrice) {
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setQuantity(quantity);
        resource.setType(type);
        resource.setUnitPrice(unitPrice);
        resource.setTotalCost();
        return ressourceRepository.save(resource);
    }

    @Override
    public Resource getResourceWithName(String resourceName) {
        return ressourceRepository.findByResourceName(resourceName);
    }
}

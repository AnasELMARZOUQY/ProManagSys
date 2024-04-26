package com.example.demo.services;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Project;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Component
public interface IResource {

    List<Resource> getAllResources();
    Resource createResource(String resourceName, Integer quantity, String type, Double unitPrice);
    Optional<Resource> getResourceWithID(Long id);
    Resource getResourceWithName(String resourceName);
    Integer removeResource(Long idResource, Long idUser);

    Resource updateResource(Resource r, Long idResource);
}

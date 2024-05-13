package com.example.demo.services;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Resource;

import java.util.List;
import java.util.Optional;

@Component
public interface IResource {

    List<Resource> getAllResources();
    Optional<Resource> createResource(String resourceName, Integer quantity, String type, Double unitPrice);
    Optional<Resource> getResourceWithID(Long id);
    Optional<Resource> getResourceWithName(String resourceName);
    Integer removeResource(Long idResource, Long idUser);
    Integer updateResource(String resourceName, Integer quantity, String type, Double unitPrice, Long idResource);
}

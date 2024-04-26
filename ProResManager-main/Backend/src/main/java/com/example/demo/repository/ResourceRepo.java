package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Resource;
import com.example.demo.entities.ResourceStatus;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {

    Resource findByResourceName(String resourceName);
    Resource findByType(String type);
    Resource findByUnitPrice(Double unitPrice);
    Resource findByStatus(ResourceStatus status);


}
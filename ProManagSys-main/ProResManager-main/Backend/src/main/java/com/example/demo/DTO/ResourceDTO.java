package com.example.demo.DTO;

import com.example.demo.entities.ResourceStatus;

import lombok.Data;

@Data
public class ResourceDTO {
    private String resourceName;
    private Double unitPrice;
    private Integer quantity;
    private String type;
    private Double totalCost;
    private ResourceStatus status;
}

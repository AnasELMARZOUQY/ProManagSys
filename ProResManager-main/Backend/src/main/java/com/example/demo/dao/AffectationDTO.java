package com.example.demo.dao;

import lombok.Data;

@Data
public class AffectationDTO{  
    private Long idAffectation;
    private String departement;
    private Long userId;
    private Long taskId;
    private Long projectId;
    
}

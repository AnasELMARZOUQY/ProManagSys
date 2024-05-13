package com.example.demo.services.impl;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import com.example.demo.services.IAffectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class AffectationServices implements IAffectation {
    @Autowired
    AffectationRepo affectationRepository ;
    @Autowired
    UserRepo userRepository;
    @Autowired
    ProjectRepo projetRepository;
    @Autowired
    TaskRepo taskRepository;
    @Autowired 
    ResourceRepo resourceRepository;
    
    @Override
    public List<Affectation> getAllAffectations(){
        return affectationRepository.findAll();
    }

    @Override
    public Optional<Affectation> getAffectationWithID(Long id){
        return affectationRepository.findById(id);
    }
    @Override
    public void deleteAffectation (Long idAffectation) {
        affectationRepository.deleteById(idAffectation);
    }

    @Override
    public List<Affectation> getAffectationsByUserId(Long userId) {
        return affectationRepository.findByUser_UserId(userId);
    }

    @Override
    public List<Affectation> getAffectationsByTaskId(Long taskId) {
        return affectationRepository.findByTask_TaskId(taskId);
    }

    @Override
    public List<Affectation> getAffectationsByProjectId(Long projectId) {
        return affectationRepository.findByProject_ProjectId(projectId);
    }
    
    @Override
    public Affectation assignTasktoUser(String taskName, String userName) {
        Affectation affectation = new Affectation();
        Optional<Task> task = taskRepository.findByTaskName(taskName);
        affectation.setTask(task.get());
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User with username " + userName + " not found");
        }
        affectation.setUser(user);
        return affectationRepository.save(affectation);
    }

    @Override
    public Affectation assignProjecttoUser(String projectName, String userName) {
        Affectation affectation = new Affectation();
        Optional<Project> project = projetRepository.findByProjectName(projectName);
        affectation.setProject(project.get());
        User user = userRepository.findByUserName(userName);
        affectation.setUser(user);
        return affectationRepository.save(affectation);
    }

    @Override
    public Affectation assignResourceToUser(String resourceName, String userName) {
        Affectation affectation = new Affectation();
        Optional<Resource> resource = resourceRepository.findByResourceName(resourceName);
        affectation.setResource(resource.get());
        User user = userRepository.findByUserName(userName);
        affectation.setUser(user);
        return affectationRepository.save(affectation);
    }


    public static List<String> getAffectationAttributes(Class<?> affectationClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = affectationClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    
    @Override
    public Affectation changeAffectation(Long id, String newUserName) {
        Affectation affectation = affectationRepository.findById(id).orElse(null);
        User newuser = userRepository.findByUserName(newUserName);
        affectation.setUser(newuser);
        return affectationRepository.save(affectation);
    }

    @Override
    public List<Affectation> getAffectationsByUserName(String userName) {
        return affectationRepository.findByUser_UserName(userName);
    }

    @Override
    public List<Affectation> getAffectationsByTaskName(String taskName) {
        return affectationRepository.findByTask_TaskName(taskName);
    }

    @Override
    public List<Affectation> getAffectationsByProjectName(String projectName) {
        return affectationRepository.findByProject_ProjectName(projectName);

    }

    //         int newSumMoisA = sumMoisA + newAffectation.getMoisA();
    //         int newSumMoisB = sumMoisB + newAffectation.getMoisB();
    //         int newSumMoisC = sumMoisC + newAffectation.getMoisC();

    //         int moisALimit = 19; // Your specific value here
    //         int moisBLimit = 16;
    //         int moisCLimit = 18;

    //         List<String> exceededMonths = new ArrayList<>();
    //         if (newSumMoisA > moisALimit)
    //             exceededMonths.add("moisA");
    //         if (newSumMoisB > moisBLimit)
    //             exceededMonths.add("moisB");
    //         if (newSumMoisC > moisCLimit)
    //             exceededMonths.add("moisC");

    //         return exceededMonths;
    //     }
    // }



    // @Override
    // public List<String> addAffectation (Affectation affectation , String idUser) {
    //     Ressources r = ressourceRepository.findByNomRessourceAndPrenomRessource(affectation.getNomRessource(), affectation.getPrenomRessource()) ;
    //     Affectation f = affectationRepository.findByProjetAndResssource(affectation.getProjet(), r);
    //     if(f != null){
    //         return null;
    //     }
    //     List<String> exceededMonths = getExceededMonths(affectation, r);
    //     if(exceededMonths.isEmpty()) {
    //         // StatuDaffectation s = statutAffRepo.findByNomstatutdaff(affectation.getStatutAff()) ;
    //         Naturedaffectation n=natureAffRepo.findByNameNaturedaffec(affectation.getNatureAff()) ;
    //         User user = userRepository.findById(idUser).orElse(null);
    //         projet project = projetRepository.findByNomprojet(affectation.getProjet());
    //         affectation.setUser(user);
    //         // affectation.setStatuDaffectations(s);
    //         affectation.setNaturedaffectations(n);
    //         affectation.setProject(project);
    //         affectation.setResssource(r);
    //         affectation.setSommeParProjet(affectation.getMoisA() + affectation.getMoisB() + affectation.getMoisC());
    //         affectationRepository.save(affectation);
    //     }
    //         return exceededMonths;
    // }






    // @Override
    // public Affectation updateAffectation(Affectation affectation, Long idAffectation){
    //     Affectation existingAffectation = affectationRepository.findById(idAffectation).orElse(null);
    //     List<String> attributes = getAffectationAttributes(Affectation.class);
    //     Set<String> ignoreProperties = new HashSet<>();
    //     for (String attribute : attributes) {
    //         if (affectation.get(attribute) == null)
    //             ignoreProperties.add(attribute);
    //     }
    //     String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
    //     BeanUtils.copyProperties(affectation, existingAffectation,ignorePropertiesArray);
    //     existingAffectation.setSommeParProjet(affectation.getMoisA()+ affectation.getMoisB()+ affectation.getMoisC());
    //     // existingAffectation.setStatuDaffectations(statutAffRepo.findByNomstatutdaff(affectation.getStatutAff()));
    //     existingAffectation.setNaturedaffectations(natureAffRepo.findByNameNaturedaffec(affectation.getNatureAff()));
    //     existingAffectation.setProject(projetRepository.findByNomprojet(affectation.getProjet()));
    //     // existingAffectation.setResssource(ressourceRepository.findByNomRessourceAndPrenomRessource(affectation.getNomRessource(), affectation.getPrenomRessource()));
    //     return affectationRepository.save(existingAffectation);
    // }



}

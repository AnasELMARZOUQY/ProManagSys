package com.example.demo.entities;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Optional;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Affectation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long affectationId;
    private String department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_task")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resource")
    private Resource resource;

    public Optional<Project> getProject() {
        return Optional.ofNullable(project);
    }

    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    public Optional<Task> getTask() {
        return Optional.ofNullable(task);
    }

    public Optional<Resource> getResource() {
        return Optional.ofNullable(resource);
    }

    // private String nomRessource;
    // private String prenomRessource;

    // private String country;
    // private String region;

    // private String natureAff;
    // private String statutAff;

    // private Integer moisA;
    // private Integer moisB;
    // private Integer moisC;

    // private Integer sommeParProjet;


    // liaison avec nature d'affectation
    // @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    // @JsonIgnore
    // @ToString.Exclude
    // @JoinColumn(name = "idNaturedaffec")
    // private Naturedaffectation naturedaffectations;


    // liaison avec statu d'affectation
    // @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    // @JsonIgnore
    // @ToString.Exclude
    // @JoinColumn(name = "idStat")
    // private StatuDaffectation statuDaffectations;


    // Many-to-one relationship with User entity
 


    // // Many-to-one relationship with Resource entity
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_ressource")
    // private Ressources resssource;


    // Many-to-one relationship with Project entity



    public Object get(String attributeName) {
        try {
            Class<?> clazz = this.getClass();
            Field field = clazz.getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}

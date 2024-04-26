package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;
    //createdat, deletedat, projectManager (reference user)
    private String description;
    private Date createdAt;
    private Date deadline;
    private ProjectStatus status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "id_manager")
    private User projectManager;
    // many to one with user
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User user;
    // one to many with affectation
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    @ToString.Exclude
    private List < Affectation > affectations;
    // private String region;
    // private String country;

    public Object get(String attributeName) {
        try {
            Class < ? > clazz = this.getClass();
            Field field = clazz.getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }




    // // liaison avec Session
    // @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @JsonIgnore
    // @JoinColumn(name = "idsession")
    // private Session session;


}
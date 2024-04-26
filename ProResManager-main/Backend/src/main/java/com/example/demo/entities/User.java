package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List < Project > userproject;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List < Project > managerproject;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List < Resource > userRessources;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List < Resource > managerRessources;

    // liaison avec feedback one to many
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List < Feedback > feedbacks;
    // liaison avec affectation one to many
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List < Affectation > affectations;

    // liaison one to many avec les ressources
    //@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true , mappedBy = "user")
    // @OneToMany(mappedBy = "user")
    // @JsonIgnore
    // @ToString.Exclude
    // private List<Ressources> resources;

    // liaison avec Session One To Many
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "user")
    // @OneToMany(mappedBy = "user")
    // @JsonIgnore
    // @ToString.Exclude
    // private List<Session> sessions;

    
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
}
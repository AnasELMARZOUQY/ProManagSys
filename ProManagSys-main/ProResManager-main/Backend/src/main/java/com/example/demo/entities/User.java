package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

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

    @ManyToOne()
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
package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Currency;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;
    private String resourceName;
    private Double unitPrice;
    private Integer quantity;
    private String type;
    private Double totalCost;
    private ResourceStatus status;

    // liaison  with user Many to one
    @ManyToOne (fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idUser")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "idManager")
    private User projectManager;

    @OneToMany(mappedBy = "resource")
    @JsonIgnore
    @ToString.Exclude
    private List<Affectation> affectations;

    // liaison avec Session
    // @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    // @JsonIgnore
    // @JoinColumn(name = "idsession")
    // private Session session;

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
    public void setTotalCost(){
        this.totalCost = this.unitPrice*(this.quantity);
    }


}


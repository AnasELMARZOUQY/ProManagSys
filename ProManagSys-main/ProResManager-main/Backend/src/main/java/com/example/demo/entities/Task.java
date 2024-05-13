package com.example.demo.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable{
    // Attributes: taskId, taskName, description, dueDate, assignedTo (reference to User), project (reference to Project), status (e.g., pending, in progress, completed)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    //Methods: createTask(), updateTask(), deleteTask(), assignTaskToUser(), updateTaskStatus()

    // liaison avec feedback one to many
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    // liaison avec affectation one to one
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Affectation> affectations;

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

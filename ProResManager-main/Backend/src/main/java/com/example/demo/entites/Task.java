package com.example.demo.entites;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    // Attributes: taskId, taskName, description, dueDate, assignedTo (reference to User), project (reference to Project), status (e.g., pending, in progress, completed)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private String description;
    private Date dueDate;
    private String status;
    //Methods: createTask(), updateTask(), deleteTask(), assignTaskToUser(), updateTaskStatus()
    public Task createTask(String taskName, String description, Date dueDate, User assignedTo, Project project, String status) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        return this;
    }
    public Task updateTask(String taskName, String description, Date dueDate, User assignedTo, Project project, String status) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        return this;
    }
    public void deleteTask() {
        this.taskName = null;
        this.description = null;
        this.dueDate = null;
        this.status = null;
    }
    public void updateTaskStatus(String status) {
        this.status = status;
    }
    // liaison avec feedback one to many
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    // liaison avec affectation one to one
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Affectation> affectations;

}

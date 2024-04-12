package com.example.demo.entites;

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

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    //Attributes: feedbackId, comment, task (reference to Task), user (reference to User)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private String comment;

    //Methods: submitFeedback(), viewFeedback()
    public Feedback submitFeedback(String comment, Task task, User user) {
        this.comment = comment;
        this.task = task;
        this.user = user;
        return this;
    }
    public Feedback viewFeedback() {
        return this;
    }
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

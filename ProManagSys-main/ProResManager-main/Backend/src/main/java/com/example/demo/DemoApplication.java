package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entities.Project;
import com.example.demo.entities.Role;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.repository.ProjectRepo;
import com.example.demo.repository.ResourceRepo;
import com.example.demo.repository.RoleDao;
import com.example.demo.repository.TaskRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.entities.Resource;


@EnableWebMvc
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class DemoApplication {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ResourceRepo resourceRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }   
    @Bean
    public CommandLineRunner demo() {
        return args -> {
            List<Role> roles = new ArrayList<>();
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin Role");
            Role userRole = new Role();
            userRole.setRoleName("Manager");
            userRole.setRoleDescription("default role for newly created record");
    
            roles.add(adminRole);
            roles.add(userRole);
    
            for (Role role : roles) {
                Optional<Role> optionalRole = roleDao.findOneByRoleName(role.getRoleName());
                if (!optionalRole.isPresent()) {
                    roleDao.save(role);
                }
            }
            List<User> users = new ArrayList<>();

            User user1 = new User();
            user1.setUserName("user1");
            user1.setUserPassword("password1");
            users.add(user1);

            User user2 = new User();
            user2.setUserName("user2");
            user2.setUserPassword("password2");
            users.add(user2);

            User user3 = new User();
            user3.setUserName("user3");
            user3.setUserPassword("password3");
            users.add(user3);

            User user4 = new User();
            user4.setUserName("user4");
            user4.setUserPassword("password4");
            users.add(user4);

            User user5 = new User();
            user5.setUserName("user5");
            user5.setUserPassword("password5");
            users.add(user5);

            for (User user : users) {
                userDao.save(user);
            }
            List<Task> tasks = new ArrayList<>();
            Task task1 = new Task();
            task1.setTaskName("Task 1");
            task1.setDescription("Description for Task 1");
            tasks.add(task1);
            Task task2 = new Task();
            task2.setTaskName("Task 2");
            task2.setDescription("Description for Task 2");
            tasks.add(task2);
            Task task3 = new Task();
            task3.setTaskName("Task 3");
            task3.setDescription("Description for Task 3");
            tasks.add(task3);
            Task task4 = new Task();
            task4.setTaskName("Task 4");
            task4.setDescription("Description for Task 4");
            tasks.add(task4);
            Task task5 = new Task();
            task5.setTaskName("Task 5");
            task5.setDescription("Description for Task 5");
            tasks.add(task5);
            for (Task task : tasks) {
                taskRepo.save(task);
            }
            List<Project> projects = new ArrayList<>();
            Project project1 = new Project();
            project1.setProjectName("Project 1");
            project1.setDescription("Description for Project 1");
            projects.add(project1);
            Project project2 = new Project();
            project2.setProjectName("Project 2");
            project2.setDescription("Description for Project 2");
            projects.add(project2);
            Project project3 = new Project();
            project3.setProjectName("Project 3");
            project3.setDescription("Description for Project 3");
            projects.add(project3);
            Project project4 = new Project();
            project4.setProjectName("Project 4");
            project4.setDescription("Description for Project 4");
            projects.add(project4);
            Project project5 = new Project();
            project5.setProjectName("Project 5");
            project5.setDescription("Description for Project 5");
            projects.add(project5);
            for (Project project : projects) {
                projectRepo.save(project);
            }
            List<Resource> resources = new ArrayList<>();
            Resource resource1 = new Resource();
            resource1.setResourceName("Resource 1");
            resources.add(resource1);
            Resource resource2 = new Resource();
            resource2.setResourceName("Resource 2");
            resources.add(resource2);
            Resource resource3 = new Resource();
            resource3.setResourceName("Resource 3");
            resources.add(resource3);
            Resource resource4 = new Resource();
            resource4.setResourceName("Resource 4");
            resources.add(resource4);
            Resource resource5 = new Resource();
            resource5.setResourceName("Resource 5");
            resources.add(resource5);
            for (Resource resource : resources) {
                resourceRepo.save(resource);
            }
        };
    }
}

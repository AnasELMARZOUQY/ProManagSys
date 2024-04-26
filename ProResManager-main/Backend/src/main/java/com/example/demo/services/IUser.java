package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Project;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Component
public interface IUser {
    List<User> getAllUsers();
    Optional<User> getUserWithID(Long id);
    void initRolesAndUser();
    Integer deleteUser(Long idUser);

    Integer updateUser(Long idUser, User user);

}

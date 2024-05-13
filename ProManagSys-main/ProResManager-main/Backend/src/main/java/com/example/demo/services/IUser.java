package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Component
public interface IUser {
    List<User> getAllUsers();
    Optional<User> getUserWithID(Long id);
    void initRolesAndUser();
    Integer deleteUser(Long idUser);
    Optional<User> registrerNewUser( String userName,String userEmail,String userFirstName,String userLastName,String userPassword, Long roleId);
    Integer updateUser(Long idUser, String userName,String userEmail,String userFirstName,String userLastName,String userPassword, Long roleId);
    Optional<User> getUserWithUserName(String userName);
}

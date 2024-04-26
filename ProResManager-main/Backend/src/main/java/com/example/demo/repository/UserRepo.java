package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    Optional<User> findOneByUserFirstName(String userFirstName);

    User findAllByUserName(String userName);
    User findByUserName(String userName);

}
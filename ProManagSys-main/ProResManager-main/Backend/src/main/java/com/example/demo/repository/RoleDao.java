package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Role;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long > {
    Optional<Role> findOneByRoleName(String roleName);
}

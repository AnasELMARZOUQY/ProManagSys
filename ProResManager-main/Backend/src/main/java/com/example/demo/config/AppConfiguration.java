package com.example.demo.config;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserdDao;
import com.example.demo.entites.Role;
import com.example.demo.entites.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class AppConfiguration implements InitializingBean {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserdDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing app...");
        this.initRoles();
        this.initAdmins();
        System.out.println("App successfully initialized");
    }

    private void initRoles() {
        System.out.println("Adding roles...");

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
    }
    private void initAdmins() {
        System.out.println("Adding admins...");

        if (!userDao.findOneByUserFirstName("admin").isPresent()) {
            User adminUser = new User();
            adminUser.setUserFirstName("admin");
            adminUser.setUserLastName("admin");
            adminUser.setUserName("admin123");
            adminUser.setUserPassword(passwordEncoder.encode("admin@pass"));
            Role adminRole = roleDao.findOneByRoleName("Admin").orElse(null);
            adminUser.setRole(adminRole);
            /*adminUser.setRole(
                    new Role() {{
                        roleDao.findOneByRoleName("Admin");
                    }}
            );*/
            userDao.save(adminUser);
        }

        if (!userDao.findOneByUserFirstName("admin2").isPresent()) {
            User adminUser = new User();
            adminUser.setUserFirstName("admin2");
            adminUser.setUserLastName("admin2");
            adminUser.setUserName("admin2");
            adminUser.setUserPassword(passwordEncoder.encode("admin@pass"));
            Role adminRole = roleDao.findOneByRoleName("Admin").orElse(null);
            adminUser.setRole(adminRole);
            /*adminUser.setRole(
                    new Role() {{
                        roleDao.findOneByRoleName("Admin");
                    }}
            );*/
            userDao.save(adminUser);
        }

    }


}


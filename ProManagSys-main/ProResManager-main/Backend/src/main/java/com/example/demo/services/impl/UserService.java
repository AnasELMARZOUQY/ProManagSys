package com.example.demo.services.impl;


import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleDao;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepo userDao;
    @Autowired
    private RoleDao roleDao;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> getUserWithID(Long id){
        return userDao.findById(id);
    }

    public Optional<User> registrerNewUser(String userName,
    String userEmail,
    String userFirstName,
    String userLastName,String userPassword, Long roleId) {
        User user = new User();
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        user.setUserPassword(userPassword);
        Role role = roleDao.findById(roleId).get();
        user.setRole(role);
        userDao.save(user);
        return Optional.of(user);
    }

    @Override
    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role managerRole = new Role();
        managerRole.setRoleName("Manager");
        managerRole.setRoleDescription("default role for newly created record");
        roleDao.save(managerRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setRole(adminRole);
        userDao.save(adminUser);
    }

    public String getEncodedPassword(String password) {
        // return passwordEncoder.encode(password);
        return null;
    }


    public static List<String> getUserAttributes(Class<?> userClass){
        List<String> attributeNames = new ArrayList<>();
        Field[] fields =userClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    // public Integer updateUser(Long idUser, User user){
    //     User existingUser = userDao.findById(idUser).get();
    //     if(existingUser != null){
    //         List<String> attributes = getUserAttributes(User.class);
    //         Set<String> ignoreProperties = new HashSet<>();
    //         for (String attribute : attributes) {
    //             if (user.get(attribute) == null)
    //                 ignoreProperties.add(attribute);
    //         }
    //         String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
    //         BeanUtils.copyProperties(user, existingUser, ignorePropertiesArray);
    //         if(existingUser.getRole() != null){
    //             Role existingRole = roleDao.findOneByRoleName(existingUser.getRole().getRoleName()).orElse(null);
    //             if (existingRole != null) {
    //                 existingUser.setRole(existingRole);
    //             }
    //         }
    //         userDao.save(existingUser);
    //     }

    //     return 1;
    // }

    public Integer updateUser(Long idUser, String userName,
    String userEmail,
    String userFirstName,
    String userLastName,String userPassword, Long roleId){
        User existingUser = userDao.findById(idUser).get();
        if(existingUser != null){
            existingUser.setUserFirstName(userFirstName);
            existingUser.setUserLastName(userLastName);
            existingUser.setUserEmail(userEmail);
            existingUser.setUserName(userName);
            existingUser.setUserPassword(userPassword);
            Role role = roleDao.findById(roleId).get();
            existingUser.setRole(role);
            userDao.save(existingUser);
        }

        return 1;
    }

    @Override
    public Integer deleteUser(Long idUser) {
        userDao.deleteById(idUser);
        return 1;
    }

    @Override
    public Optional<User> getUserWithUserName(String userName) {
        return userDao.findOneByUserName(userName);
    }
    
}

package com.example.demo.controller;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.User;
import com.example.demo.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            UserDTO userDTO = convertToDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @GetMapping("/getbyid/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") Long idUser) {
        Optional<User> user = userService.getUserWithID(idUser);
        ApiResponse response = new ApiResponse();
        if (user.isPresent()){
            response.setMessage("user found");
            UserDTO userDTO = convertToDTO(user.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK );
        }
        else {
            response.setMessage("user not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyname/{userName}")
    public ResponseEntity<?> getUserByName(@PathVariable("userName") String userName) {
        Optional<User> user = userService.getUserWithUserName(userName);
        ApiResponse response = new ApiResponse();
        if (user.isPresent()){
            UserDTO userDTO = convertToDTO(user.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK );
        }
        else {
            response.setMessage("user not found");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        return userDTO;
    }

    // @PostMapping({"/RoleAndUser"})     //////////////////////USELESS////////////////////////
    // public void initRoleAndUser() {
    //     userService.initRolesAndUser();
    // }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestParam String userName, 
                                                   @RequestParam String userEmail, 
                                                   @RequestParam String userFirstName, 
                                                   @RequestParam String userLastName, 
                                                   @RequestParam String userPassword, 
                                                   @RequestParam Long roleId) {
        Optional<User> user = userService.registrerNewUser(userName, userEmail, userFirstName, userLastName, userPassword, roleId);
        ApiResponse response = new ApiResponse();
        if(user.isPresent()){
            UserDTO userDTO = convertToDTO(user.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK );
        }
        else{
            response.setMessage("User cannot be created !");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    // @GetMapping({"/forAdmin"})
    // @PreAuthorize("hasRole('Admin')")
    // public String forAdmin() {
    //     return "this is for Admin";
    // }


    // @GetMapping({"/forManager"})
    // @PreAuthorize("hasRole('Manager')")
    // public String forUser() {
    //     return "this is for Manager";
    // }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser, 
                                              @RequestParam String userName, 
                                              @RequestParam String userEmail, 
                                              @RequestParam String userFirstName, 
                                              @RequestParam String userLastName, 
                                              @RequestParam String userPassword, 
                                              @RequestParam Long roleId) {
        Integer result = userService.updateUser(idUser, userName, userEmail, userFirstName, userLastName, userPassword, roleId);
        ApiResponse response = new ApiResponse();
        if (result != null) {
            response.setMessage("User updated !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("User cannot be updated !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping({"/remove/{idUser}"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> removeUser(@PathVariable("idUser") Long idUser){
        Integer test = userService.deleteUser(idUser);
        ApiResponse response = new ApiResponse();
        if(test != null){
            response.setMessage("User deleted !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a user");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}

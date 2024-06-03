package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping("/user")
    public ResponseEntity<User> getUserProfileHandler(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.getProfile(userDTO), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping("/admin_user")
    public ResponseEntity<List<User>> getAllUsersHandler(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }
}

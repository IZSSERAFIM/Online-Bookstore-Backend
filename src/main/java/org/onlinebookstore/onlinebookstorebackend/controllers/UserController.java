package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

//    @CrossOrigin
    @RequestMapping("/user")
    public ResponseEntity<User> getUserProfileHandler(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.getProfile(userDTO), HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping("/admin_user")
    public ResponseEntity<List<User>> getAllUsersHandler() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping(value ="/ban_user", method = RequestMethod.POST)
    public ResponseEntity<String> banUserHandler(@RequestBody UserDTO userDTO) {
        userService.banUser(userDTO);
        System.out.println("User has been banned");
        return new ResponseEntity<>("User has been banned", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value ="/unban_user", method = RequestMethod.POST)
    public ResponseEntity<String> unbanUserHandler(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        userService.unbanUser(userDTO);
        System.out.println("User has been unbanned");
        return new ResponseEntity<>("User has been unbanned", HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updateProfileHandler(@RequestBody UserDTO userDTO) {
        String result = userService.updateProfile(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", result);

        if (result.equals("SUCCESS")) {
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }
}

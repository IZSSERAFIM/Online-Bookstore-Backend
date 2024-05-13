package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public User getUser() {
        User user = new User(1, "johnsmith", "password123", "../imgs/avatar.jpg", "johnsmith@example.com", "555-1234", "123 Main Street", 5, "I love reading books!");
        return user;
    }
}

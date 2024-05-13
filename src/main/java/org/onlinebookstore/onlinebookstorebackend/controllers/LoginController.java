package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.onlinebookstore.onlinebookstorebackend.entity.User;


import java.util.Map;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        // 在这里比较用户名和密码，例如：
        boolean success = "johnsmith".equals(username) && "password123".equals(password);

        return ResponseEntity.ok(Map.of("success", success));
    }
}

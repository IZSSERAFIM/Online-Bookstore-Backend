package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.onlinebookstore.onlinebookstorebackend.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @CrossOrigin
    @RequestMapping("/login")
    public ResponseEntity<JSONObject> checkAccountHandler(@RequestBody UserDTO user) {
        HttpSession session = SessionUtils.getSession();
        String result = loginService.checkAccount(user);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", result);

        if (result.equals("SUCCESS")) {
            if (session != null) {
                session.setAttribute("userId", user);
            }
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin
    @RequestMapping("/logout")
    public @ResponseBody boolean logoutHandler(@RequestBody UserDTO user) {
        HttpSession session = SessionUtils.getSession();
        if (session != null && session.getAttribute("userId") != null) {
            session.invalidate();
            System.out.println("Session is invalidated");
            return true;
        }
        return false;
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> registerHandler(@RequestBody UserDTO userDTO) {
        String result = loginService.register(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", result);

        if (result.equals("SUCCESS")) {
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }
}

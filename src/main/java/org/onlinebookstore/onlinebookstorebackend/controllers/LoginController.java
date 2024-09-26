package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.onlinebookstore.onlinebookstorebackend.service.TimerService;
import org.onlinebookstore.onlinebookstorebackend.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
@Scope("session")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    TimerService timerService;

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
                System.out.println(session);
                timerService.startTimer();
            }
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin
    @RequestMapping("/logout")
    public @ResponseBody Map<String, Object> logoutHandler(@RequestBody UserDTO user) {
        HttpSession session = SessionUtils.getSession();
        Map<String, Object> response = new HashMap<>();
        if (session != null && session.getAttribute("userId") != null) {
            session.invalidate();
            System.out.println("Session is invalidated");
            long timeElapsed = timerService.stopTimer();
            System.out.println("Time elapsed: " + timeElapsed + "ms");
            response.put("timeElapsed", timeElapsed);
            response.put("message", "Session invalidated successfully");
        } else {
            response.put("message", "Session is already invalidated");
        }
        return response;
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

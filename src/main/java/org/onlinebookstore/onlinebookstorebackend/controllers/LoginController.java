package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.UserService;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.onlinebookstore.onlinebookstorebackend.utils.SessionUtils;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @CrossOrigin
    @RequestMapping("/login")
    public @ResponseBody boolean checkAccountHandler(@RequestBody UserDTO user){
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            session.setAttribute("userId", user);
        }
        return loginService.checkAccount(user);
    }

    @CrossOrigin
    @RequestMapping("/logout")
    public @ResponseBody boolean logoutHandler(@RequestBody UserDTO user){
        HttpSession session = SessionUtils.getSession();
        if (session != null && session.getAttribute("userId") != null) {
            session.invalidate();
            System.out.println("Session is invalidated");
            return true;
        }
        return false;
    }
}


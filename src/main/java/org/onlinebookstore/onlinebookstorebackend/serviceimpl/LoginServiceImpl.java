package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserDao userdao;

    @Override
    public String checkAccount(UserDTO user){
        String input = user.getPassword();
        String password = userdao.getPasswordByName(user.getName());
        boolean isBanned = userdao.isBanned(user.getName());

        System.out.println(input + "\n" + password);

        if (isBanned) {
            return "BANNED";
        } else if (Objects.equals(input, password)) {
            return "SUCCESS";
        } else {
            return "WRONG_CREDENTIALS";
        }
    }
}
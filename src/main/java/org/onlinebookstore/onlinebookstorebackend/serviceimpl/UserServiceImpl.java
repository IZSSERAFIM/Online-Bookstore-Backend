package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.service.UserService;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userdao;

    @Autowired
    LoginService loginService;

    @Override
    public User getProfile(UserDTO userDTO){
        return userdao.getByName(userDTO.getName());
    }

    @Override
    public List<User> getAllUsers(){
        return userdao.getAllUsers();
    }
}

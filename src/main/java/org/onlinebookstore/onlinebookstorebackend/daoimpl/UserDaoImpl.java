package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.entity.UserAuth;
import org.onlinebookstore.onlinebookstorebackend.repository.UserRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.UserAuthRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

@Service
public class UserDaoImpl implements UserDao {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserAuthRepository userAuthRepository;

    @Override
    public String getPasswordByName(String name) {
        User user = userRepository.findByName(name);
        UserAuth userAuth = userAuthRepository.findById(user.getId()).orElse(null);
        assert userAuth != null;
        return userAuth.getPassword();
    }

    @Override
    public User getByName(String name){
        return userRepository.findByName(name);
    }
}
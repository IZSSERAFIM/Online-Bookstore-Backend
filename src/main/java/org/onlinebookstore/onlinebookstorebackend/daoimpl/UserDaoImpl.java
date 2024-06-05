package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.entity.UserAuth;
import org.onlinebookstore.onlinebookstorebackend.repository.UserRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.UserAuthRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

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
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isBanned(String name) {
        User user = userRepository.findByName(name);
        return user.getBanned();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
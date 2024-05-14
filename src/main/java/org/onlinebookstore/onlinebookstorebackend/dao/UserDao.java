package org.onlinebookstore.onlinebookstorebackend.dao;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.entity.UserAuth;
import org.onlinebookstore.onlinebookstorebackend.repository.UserRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.UserAuthRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    @Resource
    UserRepository userRepository;
    @Resource
    UserAuthRepository userAuthRepository;

    public String getPasswordByName(String name) {
        User user = userRepository.findByName(name);
        UserAuth userAuth = userAuthRepository.findById(user.getId()).orElse(null);
        assert userAuth != null;
        return userAuth.getPassword();
    }

    public User getByName(String name){
        return userRepository.findByName(name);
    }
}

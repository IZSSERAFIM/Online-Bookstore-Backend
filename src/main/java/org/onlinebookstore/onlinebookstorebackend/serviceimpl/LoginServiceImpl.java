package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.entity.UserAuth;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.repository.UserAuthRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.UserRepository;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserDao userdao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public String checkAccount(UserDTO user){
        String input = user.getPassword();
        String password = userdao.getPasswordByName(user.getName());
        boolean isBanned = userdao.isBanned(user.getName());
        if (isBanned) {
            return "BANNED";
        } else if (Objects.equals(input, password)) {
            return "SUCCESS";
        } else {
            return "WRONG_CREDENTIALS";
        }
    }

    @Override
    public String register(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBanned(false); // Set banned to false for new users
        user.setLevel(1);
        userRepository.save(user);

        UserAuth userAuth = new UserAuth();
        userAuth.setId(user.getId());
        userAuth.setPassword(userDTO.getPassword());
        userAuthRepository.save(userAuth);

        return "SUCCESS";
    }
}
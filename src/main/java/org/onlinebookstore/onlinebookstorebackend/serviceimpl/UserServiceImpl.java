package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.service.UserService;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.onlinebookstore.onlinebookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userdao;

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User getProfile(UserDTO userDTO) {
        return userdao.getByName(userDTO.getName());
    }

    @Override
    public List<User> getAllUsers() {
        return userdao.getAllUsers();
    }

    @Override
    public void banUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        user.setBanned(true);
        userRepository.save(user);
    }

    @Override
    public void unbanUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setBanned(false);
        userRepository.save(user);
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String updateProfile(UserDTO userDTO) {
        if (userDTO.getName() == null) {
            return "User name must not be null";
        }

        User user = userdao.getByName(userDTO.getName());
        if (user == null) {
            return "User not found";
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setDescription(userDTO.getDescription());
        User savedUser = userRepository.findById(user.getId()).orElse(null);
        String newAvatarName = "/avatar" + savedUser.getId() + ".jpg";
        savedUser.setAvatar(newAvatarName);
        userRepository.save(user);
        // Rename the uploaded file
        File oldFile = new File(uploadPath + "temp.jpg");
        File newFile = new File(uploadPath + newAvatarName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed successfully");
        } else {
            System.out.println("Failed to rename file");
        }
        return "SUCCESS";
    }
}

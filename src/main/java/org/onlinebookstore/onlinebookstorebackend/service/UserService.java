package org.onlinebookstore.onlinebookstorebackend.service;

import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;

import java.util.List;

public interface UserService {
    public User getProfile(UserDTO userDTO);
    public List<User> getAllUsers();
    void banUser(UserDTO userDTO);
    void unbanUser(UserDTO userDTO);
    String updateProfile(UserDTO userDTO);
}

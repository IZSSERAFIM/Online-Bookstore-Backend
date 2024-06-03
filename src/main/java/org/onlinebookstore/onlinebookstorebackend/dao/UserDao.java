package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.User;

import java.util.List;

public interface UserDao {
    String getPasswordByName(String name);
    User getByName(String name);
    List<User> getAllUsers();
    boolean isBanned(String name);
}
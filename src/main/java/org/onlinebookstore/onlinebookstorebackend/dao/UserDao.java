package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.User;

public interface UserDao {
    String getPasswordByName(String name);
    User getByName(String name);
}
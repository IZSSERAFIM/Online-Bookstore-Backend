package org.onlinebookstore.onlinebookstorebackend.service;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;

public interface LoginService {
    public String checkAccount(UserDTO user);
}

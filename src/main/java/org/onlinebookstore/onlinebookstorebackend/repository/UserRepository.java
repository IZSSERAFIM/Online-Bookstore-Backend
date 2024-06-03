package org.onlinebookstore.onlinebookstorebackend.repository;
import org.springframework.stereotype.Repository;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
    public List<User> findAll();
}

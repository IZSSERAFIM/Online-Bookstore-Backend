package org.onlinebookstore.onlinebookstorebackend.repository;
import org.springframework.stereotype.Repository;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
}

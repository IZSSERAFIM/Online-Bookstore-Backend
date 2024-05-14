package org.onlinebookstore.onlinebookstorebackend.repository;
import org.springframework.stereotype.Repository;
import org.onlinebookstore.onlinebookstorebackend.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer>{
}

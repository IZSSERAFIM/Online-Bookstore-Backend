package org.onlinebookstore.onlinebookstorebackend.repository;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer>{
}

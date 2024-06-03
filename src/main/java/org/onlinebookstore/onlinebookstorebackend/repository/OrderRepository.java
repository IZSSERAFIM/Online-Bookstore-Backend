package org.onlinebookstore.onlinebookstorebackend.repository;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}

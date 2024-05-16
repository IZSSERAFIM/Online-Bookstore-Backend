package org.onlinebookstore.onlinebookstorebackend.repository;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    public List<OrderItem> findByOrderId(Integer id);
}

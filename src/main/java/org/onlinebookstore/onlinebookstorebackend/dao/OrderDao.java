package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import java.util.List;

public interface OrderDao {
    void addOrder(Order order);
    List<Order> getAllOrders();
}
package org.onlinebookstore.onlinebookstorebackend.service;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    public boolean addOrder(OrderDTO order);
    public List<Order> getAllOrders(UserDTO userDTO);
}

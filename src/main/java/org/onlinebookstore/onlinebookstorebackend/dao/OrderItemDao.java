package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import java.util.List;

public interface OrderItemDao {
    void addOrderItems(List<OrderItem> orderItems);
}
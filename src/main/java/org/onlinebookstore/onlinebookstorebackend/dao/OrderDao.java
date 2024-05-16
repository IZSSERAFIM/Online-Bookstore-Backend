package org.onlinebookstore.onlinebookstorebackend.dao;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderItemRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDao {
    @Resource
    OrderRepository orderRepo;
    @Resource
    OrderItemRepository orderItemRepo;

    public void addOrder(Order order) {

        Order savedOrder = orderRepo.save(order);
        for(OrderItem orderItem : order.getOrderItemList())
            orderItem.setOrder(savedOrder);
        orderItemRepo.saveAll(order.getOrderItemList());
    }

    public List<Order> getAllOrders(){
        List<Order> orderList = orderRepo.findAll();
        return orderList;
    }
}

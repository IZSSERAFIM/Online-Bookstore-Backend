package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.OrderDao;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

@Service
public class OrderDaoImpl implements OrderDao {
    @Resource
    private OrderRepository orderRepo;
    @Resource
    private OrderItemRepository orderItemRepo;

    @Override
    public void addOrder(Order order) {
        Order savedOrder = orderRepo.save(order);
        for(OrderItem orderItem : order.getOrderItemList())
            orderItem.setOrder(savedOrder);
        orderItemRepo.saveAll(order.getOrderItemList());
    }

    @Override
    public List<Order> getAllOrders(){
        List<Order> orderList = orderRepo.findAll();
        return orderList;
    }
}
package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.OrderItemDao;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemDaoImpl implements OrderItemDao {
    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void addOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
//        int result = 1 / 0;
    }
}
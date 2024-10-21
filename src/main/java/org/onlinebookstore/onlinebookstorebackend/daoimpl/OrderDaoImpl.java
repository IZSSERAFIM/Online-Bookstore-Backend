package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.OrderDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderRepository;
import org.onlinebookstore.onlinebookstorebackend.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDaoImpl implements OrderDao {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(UserDTO userDTO){
        User user = userDao.getByName(userDTO.getName());
        List<Order> orderList = orderRepository.findByUser(user);
        return orderList;
    }

    @Override
    public List<Order> getAllOrders(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }
}
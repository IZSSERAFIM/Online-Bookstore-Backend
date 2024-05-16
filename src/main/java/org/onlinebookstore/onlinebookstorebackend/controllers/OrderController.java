package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin
    @RequestMapping("/order/add")
    public boolean addOrderHandler(@RequestBody OrderDTO orderDTO){
        return orderService.addOrder(orderDTO);
    }

    @CrossOrigin
    @RequestMapping("/order")
    public List<Order> getAllOrdersHandler(@RequestBody UserDTO userDTO){
        return orderService.getAllOrders(userDTO);
    }
}

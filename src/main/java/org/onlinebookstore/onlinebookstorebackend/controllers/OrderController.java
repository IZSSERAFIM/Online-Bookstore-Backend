package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @CrossOrigin
    @RequestMapping("/order/add")
    public boolean addOrderHandler(@RequestBody OrderDTO orderDTO){
        String data = orderDTO.getDate() + "," + orderDTO.getName() + "," + orderDTO.getBookIdList() + "," + orderDTO.getBookNumList();
        kafkaTemplate.send("order", "key", data);
        System.out.println("Sent message: " + data);
        return true;
    }

//    @CrossOrigin
    @RequestMapping("/order")
    public List<Order> getAllOrdersHandler(@RequestBody UserDTO userDTO){
        return orderService.getAllOrders(userDTO);
    }

//    @CrossOrigin
    @RequestMapping("/admin_order")
    public List<Order> getAllOrdersHandler(){
        return orderService.getAllOrders();
    }
}

package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<Order> getOrders() {
        // 创建一些书的订单
        List<Order.Item> items1 = new ArrayList<>();
        items1.add(new Order.Item(1, new Book(1, "书1", "作者1", "描述1", 100, "封面1", 10, 200), 2));
        items1.add(new Order.Item(2, new Book(2, "书2", "作者2", "描述2", 200, "封面2", 20, 200), 3));

        List<Order.Item> items2 = new ArrayList<>();
        items2.add(new Order.Item(3, new Book(3, "书3", "作者3", "描述3", 300, "封面3", 30, 200), 4));
        items2.add(new Order.Item(4, new Book(4, "书4", "作者4", "描述4", 400, "封面4", 40, 200), 5));

        return Arrays.asList(
                new Order(1, "张三", "上海市浦东新区", "13800000000", "2021-06-01", items1),
                new Order(2, "李四", "上海市徐汇区", "13800000001", "2021-06-02", items2),
                new Order(3, "王五", "上海市静安区", "13800000002", "2021-06-03", new ArrayList<>()),
                new Order(4, "赵六", "上海市黄浦区", "13800000003", "2021-06-04", new ArrayList<>())
        );
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody Map<String, String> payload) {
        String receiver = payload.get("receiver");
        String address = payload.get("address");
        String tel = payload.get("tel");
        String createdAt = payload.get("createdAt");
        // 在这里处理订单逻辑，例如添加到数据库等
        System.out.println("Received order for receiver: " + receiver);
        return ResponseEntity.ok("订单确认");
    }
}
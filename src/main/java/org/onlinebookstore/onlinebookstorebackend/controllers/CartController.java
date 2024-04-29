package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.Cart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @GetMapping
    public List<Cart> getCarts() {
        return Arrays.asList(
                new Cart(1, "《芯片简史》", 6600, "../imgs/book1.jpg", 1),
                new Cart(2, "《深入理解计算机系统》", 5900, "../imgs/book2.jpg", 2),
                new Cart(3, "《编程珠玑》", 4900, "../imgs/book3.jpg", 1),
                new Cart(4, "《代码大全》", 8800, "../imgs/book4.jpg", 1)
        );
    }

}

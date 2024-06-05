package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.onlinebookstore.onlinebookstorebackend.service.CartService;
import org.onlinebookstore.onlinebookstorebackend.dto.CartDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @CrossOrigin
    @RequestMapping("/cart")
    public ResponseEntity<List<CartItem>> getCartBooksHandler(@RequestBody UserDTO userDTO){
        System.out.println("UserDTO: " + userDTO);
        return new ResponseEntity<>(cartService.getAllCartBooks(userDTO), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping("/cart/add")
    public boolean addCartBookHandler(@RequestBody CartDTO cartBook){
        return cartService.addCartBook(cartBook);
    }

    @CrossOrigin
    @RequestMapping("/cart/delete")
    public boolean deleteCartHandler(@RequestBody Integer cartId){
        return cartService.deleteCart(cartId);
    }
}

package org.onlinebookstore.onlinebookstorebackend.dao;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.onlinebookstore.onlinebookstorebackend.repository.CartRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDao {
    @Resource
    CartRepository cartRepository;

    public List<CartItem> getAllCartBooks(){
        List<CartItem> cartItemList = cartRepository.findAll();
        return cartItemList;
    }

    public void addCartBook(CartItem cartItem){
        cartRepository.save(cartItem);
    }
}

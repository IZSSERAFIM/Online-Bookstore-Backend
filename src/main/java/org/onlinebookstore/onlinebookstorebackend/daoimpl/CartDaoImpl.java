package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.CartDao;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.onlinebookstore.onlinebookstorebackend.repository.CartRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

@Service
public class CartDaoImpl implements CartDao {
    @Resource
    private CartRepository cartRepository;

    @Override
    public List<CartItem> getAllCartBooks(){
        List<CartItem> cartItemList = cartRepository.findAll();
        return cartItemList;
    }

    @Override
    public void addCartBook(CartItem cartItem){
        cartRepository.save(cartItem);
    }
}
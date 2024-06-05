package org.onlinebookstore.onlinebookstorebackend.service;

import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.onlinebookstore.onlinebookstorebackend.dto.CartDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;

import java.util.List;

public interface CartService {
    public boolean addCartBook(CartDTO cartBook);
    public List<CartItem> getAllCartBooks(UserDTO userDTO);
    public boolean deleteCart(Integer cartId);
}

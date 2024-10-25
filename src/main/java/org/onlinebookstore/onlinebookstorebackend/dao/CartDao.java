package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import java.util.List;

public interface CartDao {
    List<CartItem> getAllCartBooks(UserDTO userDTO);
    void addCartBook(CartItem cartItem);
    void deleteCart(Integer cartId);

    CartItem getCartItemById(Integer cartId);
}
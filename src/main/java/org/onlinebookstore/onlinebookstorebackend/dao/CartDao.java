package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import java.util.List;

public interface CartDao {
    List<CartItem> getAllCartBooks();
    void addCartBook(CartItem cartItem);
}
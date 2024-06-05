package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
import org.onlinebookstore.onlinebookstorebackend.service.CartService;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.CartDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.onlinebookstore.onlinebookstorebackend.dao.CartDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartdao;
    @Autowired
    BookDao bookdao;
    @Autowired
    UserDao userdao;

    @Override
    public boolean addCartBook(CartDTO cartItemDTO){
        CartItem cartItem = new CartItem(null,
                cartItemDTO.getDate(),
                bookdao.getBookById(cartItemDTO.getBookId()),
                userdao.getByName(cartItemDTO.getName()));
        cartdao.addCartBook(cartItem);
        return true;
    }

    @Override
    public List<CartItem> getAllCartBooks(UserDTO userDTO){
        return cartdao.getAllCartBooks(userDTO);
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        try {
            cartdao.deleteCart(cartId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

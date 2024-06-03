package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.CartDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.repository.CartRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

@Service
public class CartDaoImpl implements CartDao {
    @Resource
    private CartRepository cartRepository;
    @Resource
    private UserDao userDao;

    @Override
    public List<CartItem> getAllCartBooks(UserDTO userDTO){
        User user = userDao.getByName(userDTO.getName());
        List<CartItem> cartItemList = cartRepository.findByUser(user);
        return cartItemList;
    }

    @Override
    public void addCartBook(CartItem cartItem){
        cartRepository.save(cartItem);
    }
}
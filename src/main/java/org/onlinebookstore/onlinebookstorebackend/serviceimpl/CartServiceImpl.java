package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.service.CartService;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.CartDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.onlinebookstore.onlinebookstorebackend.dao.CartDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartdao;
    @Autowired
    BookDao bookdao;
    @Autowired
    UserDao userdao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean addCartBook(CartDTO cartItemDTO) {
        CartItem cartItem = new CartItem(null,
                cartItemDTO.getDate(),
                bookdao.getBookById(cartItemDTO.getBookId()),
                userdao.getByName(cartItemDTO.getName()));
        cartdao.addCartBook(cartItem);

        // Update the cache with the new list of cart items
        UserDTO userDTO = new UserDTO(cartItemDTO.getName());
        List<CartItem> updatedCartItems = cartdao.getAllCartBooks(userDTO);
        redisTemplate.opsForValue().set("cart::" + cartItemDTO.getName(), updatedCartItems);

        return true;
    }

    @Override
    @Cacheable(value = "cart", key = "#userDTO.name")
    public List<CartItem> getAllCartBooks(UserDTO userDTO) {
        return cartdao.getAllCartBooks(userDTO);
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        try {
            CartItem cartItem = cartdao.getCartItemById(cartId);
            if (cartItem != null) {
                cartdao.deleteCart(cartId);

                // Update the cache with the new list of cart items
                List<CartItem> updatedCartItems = cartdao.getAllCartBooks(new UserDTO(cartItem.getUser().getName()));
                redisTemplate.opsForValue().set("cart::" + cartItem.getUser().getName(), updatedCartItems);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
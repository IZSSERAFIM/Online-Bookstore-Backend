package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.OrderItemDao;
import org.onlinebookstore.onlinebookstorebackend.service.OrderService;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.dto.UserDTO;
import org.onlinebookstore.onlinebookstorebackend.dao.OrderDao;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.entity.OrderItem;
import org.onlinebookstore.onlinebookstorebackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderdao;
    @Autowired
    BookDao bookdao;
    @Autowired
    UserDao userdao;
    @Autowired
    LoginService loginService;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addOrder(OrderDTO orderDto) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < orderDto.getBookIdList().size(); i++) {
            orderItemList.add(new OrderItem(null,
                    bookdao.getBookById(orderDto.getBookIdList().get(i)),
                    null,
                    orderDto.getBookNumList().get(i)));
            System.out.println(orderItemList.get(orderItemList.size() - 1));
        }
        Order order = new Order(null, orderDto.getDate(), orderItemList, userdao.getByName(orderDto.getName()));
        orderdao.addOrder(order);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder(order);
        }
        orderItemDao.addOrderItems(orderItemList);
//        redisTemplate.opsForValue().set("order::" + orderDto.getName(), orderdao.getAllOrders(new UserDTO(orderDto.getName())));
        return true;
    }

    @Override
    public boolean checkStockAndAddOrder(OrderDTO orderDto) {
        for (int i = 0; i < orderDto.getBookIdList().size(); i++) {
            Book book = bookdao.getBookById(orderDto.getBookIdList().get(i));
            if (book.getStock() < orderDto.getBookNumList().get(i)) {
                return false;
            }
        }
        addOrder(orderDto);
        for (int i = 0; i < orderDto.getBookIdList().size(); i++) {
            Book book = bookdao.getBookById(orderDto.getBookIdList().get(i));
            book.setSales(book.getSales() + orderDto.getBookNumList().get(i));
            book.setStock(book.getStock() - orderDto.getBookNumList().get(i));
            bookdao.save(book);
        }

        // Get the user and increase their level
        User user = userdao.getByName(orderDto.getName());
        user.setLevel(user.getLevel() + 1);
        userdao.save(user);

        return true;
    }

    @Override
//    @Cacheable(value = "order", key = "#userDTO.name")
    public List<Order> getAllOrders(UserDTO userDTO) {
        return orderdao.getAllOrders(userDTO);
    }

    @Override
//    @Cacheable(value = "allOrders")
    public List<Order> getAllOrders() {
        return orderdao.getAllOrders();
    }
}

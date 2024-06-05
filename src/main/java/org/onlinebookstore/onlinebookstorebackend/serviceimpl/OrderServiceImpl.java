package org.onlinebookstore.onlinebookstorebackend.serviceimpl;
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
import org.springframework.stereotype.Service;

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

    @Override
    public boolean addOrder(OrderDTO orderDto){
        List<OrderItem> orderItemList = new ArrayList<>();
        for(int i = 0; i < orderDto.getBookIdList().size(); i ++){
            orderItemList.add(new OrderItem(null,
                    bookdao.getBookById(orderDto.getBookIdList().get(i)),
                    null,
                    orderDto.getBookNumList().get(i)));
            System.out.println(orderItemList.get(orderItemList.size() - 1));
        }
        orderdao.addOrder(new Order(null, orderDto.getDate(), orderItemList, userdao.getByName(orderDto.getName())));
        return true;
    }

    @Override
public boolean checkStockAndAddOrder(OrderDTO orderDto){
    for(int i = 0; i < orderDto.getBookIdList().size(); i ++){
        Book book = bookdao.getBookById(orderDto.getBookIdList().get(i));
        if(book.getStock() < orderDto.getBookNumList().get(i)){
            return false;
        }
    }
    addOrder(orderDto);
    for(int i = 0; i < orderDto.getBookIdList().size(); i ++){
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
    public List<Order> getAllOrders(UserDTO userDTO){
        return orderdao.getAllOrders(userDTO);
    }
}

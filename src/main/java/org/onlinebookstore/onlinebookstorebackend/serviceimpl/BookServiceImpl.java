package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookDao bookdao;

    @Override
    public List<Book> getAllBooks(){
        return bookdao.getAllBooks();
    }

    @Override
    public Book getBookById(Integer id){
        return bookdao.getBookById(id);
    }
}

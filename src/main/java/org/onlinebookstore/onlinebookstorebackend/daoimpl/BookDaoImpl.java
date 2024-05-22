package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

@Service
public class BookDaoImpl implements BookDao {
    @Resource
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public Book getBookById(Integer id){
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }
}
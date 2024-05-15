package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDao {
    @Resource
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    public Book getBookById(Integer id){
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }
}

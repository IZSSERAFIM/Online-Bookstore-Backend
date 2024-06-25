package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(Integer id);
    void save(Book book);
    void addBook(Book book);
}
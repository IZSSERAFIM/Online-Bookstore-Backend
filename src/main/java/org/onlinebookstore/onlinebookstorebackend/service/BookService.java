package org.onlinebookstore.onlinebookstorebackend.service;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public Book getBookById(Integer id);
}

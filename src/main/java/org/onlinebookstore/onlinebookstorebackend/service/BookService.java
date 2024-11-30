package org.onlinebookstore.onlinebookstorebackend.service;
import org.onlinebookstore.onlinebookstorebackend.dto.BookDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public List<Book> getAllBooks();
    public Book getBookById(Integer id);
    public Page<Book> getAllBooks(Pageable pageable);
    public Page<Book> searchBooks(String keyword, Pageable pageable);
    public List<Book> getBestSellingBooks();
    public boolean addBook(BookDTO book);
    public boolean updateBook(BookDTO book);
    public boolean deleteBook(Integer id);
    public List<Book> findBooksByTagRelation(String tagName);
}

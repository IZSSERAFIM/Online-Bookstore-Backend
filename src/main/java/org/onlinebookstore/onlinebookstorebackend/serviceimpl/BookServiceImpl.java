package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.dto.BookDTO;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookdao;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        return bookdao.getAllBooks();
    }

    @Override
    @Cacheable(value = "book", key = "#id")
    public Book getBookById(Integer id) {
        return bookdao.getBookById(id);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.findByTitleContaining(keyword, pageable);
    }

    @Override
    @Cacheable(value = "bestSellingBooks")
    public List<Book> getBestSellingBooks() {
        return bookRepository.findTop5ByOrderBySalesDesc();
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public boolean addBook(BookDTO book) {
        Book newBook = new Book(
                null,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPrice() * 100,
                "temp.jpg", // Set the cover to a temporary value
                0,
                15,
                new ArrayList<>() // Initialize CartItems as an empty list
        );
        bookdao.addBook(newBook);
        Book savedBook = bookRepository.findByTitle(book.getTitle()); // Save the book and get the saved instance
        String newCoverName = "book" + savedBook.getId() + ".jpg";
        savedBook.setCover("http://localhost:8080/images/" + newCoverName); // Update the cover with the correct ID
        bookRepository.save(savedBook); // Save the book again with the updated cover

        // Rename the uploaded file
        File oldFile = new File(uploadPath + "temp.jpg");
        File newFile = new File(uploadPath + newCoverName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed successfully");
        } else {
            System.out.println("Failed to rename file");
        }

        // Cache the updated book
        redisTemplate.opsForValue().set("book::" + book.getId(), savedBook);

        return true;
    }

    @Override
    public boolean updateBook(BookDTO book) {
        Optional<Book> existingBookOptional = bookRepository.findById(book.getId());
        if (!existingBookOptional.isPresent()) {
            return false;
        }
        Book existingBook = existingBookOptional.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setDescription(book.getDescription());
        existingBook.setPrice(book.getPrice());
        existingBook.setStock(book.getStock());
        String coverName = "book" + existingBook.getId() + ".jpg";
        File tempFile = new File(uploadPath + "temp.jpg");
        File oldFile = new File(uploadPath + coverName);
        // Check if the old file exists before attempting to delete it
        if (tempFile.exists()) {
            if (oldFile.exists()) {
                if (oldFile.delete()) {
                    System.out.println("Old file deleted successfully: " + oldFile.getPath());
                } else {
                    System.out.println("Failed to delete old file: " + oldFile.getPath());
                    return false;
                }
            } else {
                System.out.println("Old file does not exist: " + oldFile.getPath());
            }
            // Rename the temp file to the new file
            File newFile = new File(uploadPath + coverName);
            if (tempFile.renameTo(newFile)) {
                System.out.println("File renamed successfully to: " + newFile.getPath());
            } else {
                System.out.println("Failed to rename file: " + tempFile.getPath() + " to " + newFile.getPath());
                return false;
            }
        }
        existingBook.setCover("http://localhost:8080/images/" + coverName);
        // Save the updated book details
        bookRepository.save(existingBook);

        // Cache the updated book
        redisTemplate.opsForValue().set("book::" + book.getId(), existingBook);

        return true;
    }

    @Override
    @CacheEvict(value = "book", key = "#id")
    public boolean deleteBook(Integer id) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (!existingBookOptional.isPresent()) {
            return false;
        }
        bookRepository.deleteById(id);
        System.out.println("Book deleted");
        return true;
    }
}

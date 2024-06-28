package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.dto.BookDTO;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookdao;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookdao.getAllBooks();
    }

    @Override
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
        String newCoverName = "/book" + savedBook.getId() + ".jpg";
        savedBook.setCover(newCoverName); // Update the cover with the correct ID
        bookRepository.save(savedBook); // Save the book again with the updated cover

        // Rename the uploaded file
        File oldFile = new File(uploadPath + "temp.jpg");
        File newFile = new File(uploadPath + newCoverName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed successfully");
        } else {
            System.out.println("Failed to rename file");
        }

        return true;
    }

    @Override
    public boolean updateBook(Book book) {
        Optional<Book> existingBookOptional = bookRepository.findById(book.getId());
        if (!existingBookOptional.isPresent()) {
            return false;
        }
        Book existingBook = existingBookOptional.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setDescription(book.getDescription());
        existingBook.setPrice(book.getPrice());
        existingBook.setCover(book.getCover());
        existingBook.setSales(book.getSales());
        existingBook.setStock(book.getStock());
        bookRepository.save(existingBook);
        return true;
    }

    @Override
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

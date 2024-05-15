package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @CrossOrigin
    @RequestMapping("/home")
    public ResponseEntity<List<Book>> getAllBooksHandler() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping("/book")
    public ResponseEntity<Book> getBookByIdHandler(Integer id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.ACCEPTED);
    }
}

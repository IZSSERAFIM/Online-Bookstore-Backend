package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @CrossOrigin
    @RequestMapping("/books")
    public ResponseEntity<JSONObject> searchBooksHandler(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Book> page;
        if (keyword == null || keyword.isEmpty()) {
            page = bookService.getAllBooks(pageable);
        } else {
            page = bookService.searchBooks(keyword, pageable);
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("items", page.getContent());
        jsonResponse.put("total", page.getTotalPages());
        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }
}

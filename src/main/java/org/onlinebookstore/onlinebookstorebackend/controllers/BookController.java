package org.onlinebookstore.onlinebookstorebackend.controllers;

import com.alibaba.fastjson2.JSON;
import org.onlinebookstore.onlinebookstorebackend.dto.BookDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

//    @CrossOrigin
    @RequestMapping("/home")
    public ResponseEntity<List<Book>> getAllBooksHandler() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping("/book")
    public ResponseEntity<Book> getBookByIdHandler(Integer id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
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

//    @CrossOrigin
    @RequestMapping("/books/rank")
    public ResponseEntity<JSONObject> getBestSellingBooksHandler() {
        List<Book> books = bookService.getBestSellingBooks();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("books", books);

        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public boolean addBookHandler(@RequestBody BookDTO book) {
        return bookService.addBook(book);
    }

//    @CrossOrigin
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBookHandler(@RequestBody String payload) {
        // 解析传入的 JSON 字符串为 JSONObject
        JSONObject jsonObject = JSON.parseObject(payload);

        // 从 JSONObject 中提取 BookDTO 对象
        BookDTO updatedBook = jsonObject.getObject("updatedBook", BookDTO.class);
        if (updatedBook == null) {
            return new ResponseEntity<>("Invalid request body", HttpStatus.BAD_REQUEST);
        }
        if (bookService.updateBook(updatedBook)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @CrossOrigin
    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public boolean deleteBookHandler(@RequestBody BookDTO book) {
        return bookService.deleteBook(book.getId());
    }
}

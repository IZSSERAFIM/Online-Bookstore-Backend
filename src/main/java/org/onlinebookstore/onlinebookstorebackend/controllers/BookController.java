package org.onlinebookstore.onlinebookstorebackend.controllers;

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

    @CrossOrigin
    @RequestMapping("/books/rank")
    public ResponseEntity<JSONObject> getBestSellingBooksHandler() {
        List<Book> books = bookService.getBestSellingBooks();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("books", books);

        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public boolean addBookHandler(@RequestBody BookDTO book) {
        return bookService.addBook(book);
    }

    @CrossOrigin
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBookHandler(@RequestBody Map<String, BookDTO> payload) {
        BookDTO bookid_stock = payload.get("bookid_stock");
        if (bookid_stock == null) {
            System.out.println("Invalid request body");
            return new ResponseEntity<>("Invalid request body", HttpStatus.BAD_REQUEST);
        }
        Integer id = bookid_stock.getId();
        Integer stock = bookid_stock.getStock();
        if (id == null || stock == null) {
            System.out.println("Invalid id or stock");
            return new ResponseEntity<>("Invalid id or stock", HttpStatus.BAD_REQUEST);
        }
        Book book = bookService.getBookById(id);
        if (book == null) {
            System.out.println("Book not found");
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        book.setStock(stock);
        bookService.updateBook(book);
        System.out.println("Book updated successfully");
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public boolean deleteBookHandler(@RequestBody BookDTO book) {
        return bookService.deleteBook(book.getId());
    }
}

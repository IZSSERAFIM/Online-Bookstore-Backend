package org.onlinebookstore.microservice.controllers;

import org.onlinebookstore.microservice.dto.ResultDTO;
import org.onlinebookstore.microservice.entity.Book;
import org.onlinebookstore.microservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping(value = "/getBookAuthorByName/{bookName}")
    public ResultDTO getBookAuthorByName(@PathVariable("bookName") String bookName){
        Book book = bookRepository.findBookByTitle(bookName);
        String author = book.getAuthor();
        return ResultDTO.success(JSON.toJSONString(author));
    }
}

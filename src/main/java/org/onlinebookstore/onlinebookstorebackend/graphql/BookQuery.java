package org.onlinebookstore.onlinebookstorebackend.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.onlinebookstore.onlinebookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookQuery {

    @Autowired
    private BookService bookService;

    @QueryMapping
    public Book getBookByTitle(@Argument String title) {
        return bookService.findByTitle(title);
    }
}

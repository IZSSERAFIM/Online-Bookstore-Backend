package org.onlinebookstore.onlinebookstorebackend.controllers;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    public List<Book> getBooks() {
        List<Book> books = Arrays.asList(
                new Book(1, "《芯片简史》", "杨扬", "《芯片简史》是一本关于芯片发展历史的书籍，从最早的集成电路到最新的芯片技术，全面介绍了芯片的发展历程。", 6600, "../imgs/book1.jpg", 1000, 1000),
                new Book(2, "《JavaScript 高级程序设计》", "Nicholas C.Zakas", "《JavaScript 高级程序设计》是一本全面介绍 JavaScript 语言核心概念的书籍，适合有一定 JavaScript 基础的读者。", 8800, "../imgs/book2.jpg", 10000, 8000),
                new Book(3, "《JavaScript 忍者秘籍》", "John Resig", "《JavaScript 忍者秘籍》是一本深入介绍 JavaScript 技巧和技术的书籍，适合有一定 JavaScript 基础的读者。", 10000, "../imgs/book3.jpg", 8000, 9000),
                new Book(4, "《ES6 标准入门》", "阮一峰", "《ES6 标准入门》是一本全面介绍 ES6 新特性的书籍，适合有一定 JavaScript 基础的读者。", 8800, "../imgs/book4.jpg", 5000, 6000),
                new Book(5, "《深入浅出 Node.js》", "朴灵", "《深入浅出 Node.js》是一本全面介绍 Node.js 技术的书籍，适合有一定 JavaScript 基础的读者。", 8000, "../imgs/book5.jpg", 6000, 7000),
                new Book(6, "《CSS 世界》", "张鑫旭", "《CSS 世界》是一本全面介绍 CSS 技术的书籍，适合有一定 CSS 基础的读者。", 6000, "../imgs/book6.jpg", 7000, 8000),
                new Book(7, "《CSS 揭秘》", "Lea Verou", "《CSS 揭秘》是一本深入介绍 CSS 技巧和技术的书籍，适合有一定 CSS 基础的读者。", 5000, "../imgs/book7.jpg", 8000, 9000),
                new Book(8, "《Vue.js 实战》", "梁灏", "《Vue.js 实战》是一本全面介绍 Vue.js 技术的书籍，适合有一定 Vue.js 基础的读者。", 8800, "../imgs/book8.jpg", 9000, 10000),
                new Book(9, "《TypeScript 入门教程》", "唐新一", "《TypeScript 入门教程》是一本全面介绍 TypeScript 技术的书籍，适合有一定 TypeScript 基础的读者。", 6600, "../imgs/book9.jpg", 7000, 8000),
                new Book(10, "《go》", "Alan A.A.Donovan", "《go》是一本全面介绍 go 技术的书籍，适合有一定 go 基础的读者。", 6600, "../imgs/book10.jpg", 7000, 8000)
        );
        return books;
    }
}

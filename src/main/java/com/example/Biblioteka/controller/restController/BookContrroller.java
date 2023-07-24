package com.example.Biblioteka.controller.restController;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.service.Book.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/api/book")
public class BookContrroller {
    @Autowired
    private BookService bookService;
    @GetMapping("/")
    public List<Book> getBookList() {
        return bookService.findAll();
    }

    @GetMapping(value = "add")
    public Book addBook(){
        return new Book();
    }

    @PostMapping(value = "add")
    public void addBook(Book book) {
        bookService.add(book);
    }
}

package com.example.Biblioteka.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.service.Book.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private BookRepository bookRepository;
    @Value("${sql.script.delete.book}")
    public String sqlDeleteBook;

    @Value("${sql.script.create.book}")
    public String sqlCreateBook;

    private Book book;
    @Autowired
    private BookService bookService;


    static private Book setBook() {
        Book book = new Book();
        List<Borrow> borrow = new ArrayList<>();

        book.setAuthorName("AuthorName");
        book.setAuthorSurname("AuthorSurname");
        book.setBookTitle("bookTitle");
        book.setBorrowsbook(borrow);
        return book;
    }

    @BeforeEach
    public void setup(){
        jdbc.execute(sqlCreateBook);
    }

    @Test
    public void BookExists() {
        Assertions.assertTrue(bookService.exists(setBook()));
    }

    @Test
    public void BookNotExists(){
        Book book = setBook();
        book.setBookTitle("TitleTest");
        Assertions.assertFalse(bookService.exists(book));
    }

    @Test
    public void BookShouldBeAdded()  {
        Book book = setBook();
        book.setBookTitle("TitleTest");
        if(!bookService.exists(book)){
            bookService.add(book);
        }
        Assertions.assertEquals(2,bookRepository.findAll().size());

    }
    @Test
    public void BookShouldBeNotAdded(){
        Book book = setBook();
        book.setBookTitle("bookTitle");
        if(!bookService.exists(book)){
            bookService.add(book);
        }
        Assertions.assertEquals(1,bookRepository.findAll().size());
    }
    @Test
    public void bookShouldBeRemoved() {

        Assertions.assertEquals(1,bookRepository.findAll().size());
        bookService.delate(2);
        Assertions.assertEquals(0,bookRepository.findAll().size());

    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute(sqlDeleteBook);
    }

}

package com.example.Biblioteka.controller.restController;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.service.Book.BookService;
import com.example.Biblioteka.service.Borrow.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> bookForBorrow(){
        return bookService.findAll();
    }
    @GetMapping("/myBorrow")
    public List<Borrow> borrowList(@RequestParam("id")int id){
        return borrowService.findBorrowForUser(id);
    }

    @PostMapping("/newBorrow")
    public void reservation(@RequestParam("bookId")int bookId, @RequestParam("customUser") UserDetails customUser){
        borrowService.reservation(bookId,customUser);
    }

    @PutMapping("/releaseBook")
    public void released(@RequestParam("book")int bookId){
        borrowService.releaseBook(bookId);
    }

    @PutMapping("/giveBack")
    public void giveBack(@RequestParam("bookId")int bookId){
        borrowService.giveBack(bookId);
    }



}

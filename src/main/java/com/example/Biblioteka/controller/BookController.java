package com.example.Biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.service.BookService;
@Controller
public class BookController {
	private RoleRepository roleRepository;
	private BookRepository bookRepository;
	private final BookService bookService;
	
	public BookController(RoleRepository roleRepository, BookService bookService,
			BookRepository  bookRepository) {
		super();
		this.bookRepository = bookRepository;
		this.roleRepository = roleRepository;
		this.bookService = bookService;
	}
	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "add-book";
	}
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute Book book) {
		System.out.println(bookService.exists(book));
		if(bookService.exists(book)){
			return "redirect:/addBook";
		}
		bookService.add(book);
		return "redirect:/borrow";
	}

}

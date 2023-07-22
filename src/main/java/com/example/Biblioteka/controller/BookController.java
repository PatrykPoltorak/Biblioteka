package com.example.Biblioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.service.Book.BookService;
@Controller
@AllArgsConstructor
public class BookController {
	private BookService bookService;

	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "add-book";
	}
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute Book book) {
		if(bookService.exists(book)){
			return "redirect:/addBook";
		}
		bookService.add(book);
		return "redirect:/borrow";
	}

}

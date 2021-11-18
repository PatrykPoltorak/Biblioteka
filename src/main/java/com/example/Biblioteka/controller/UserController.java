package com.example.Biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.entity.User;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.BorrowRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.*;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserRepository userRepository;
	private BookRepository bookRepository;
	private BorrowRepository borrowRepository;
	private BorrowService borrowService;
	private User user;

	public UserController(UserRepository userRepository, BookRepository bookRepository,
			BorrowRepository borrowRepository,BorrowService borrowService) {
		super();
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
		this.borrowRepository = borrowRepository;
		this.borrowService = borrowService;
	}
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("borrows", borrowRepository.findAll());	
		return "home";
	}
	
	
}

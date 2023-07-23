package com.example.Biblioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Biblioteka.entity.Users;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.BorrowRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.*;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
	private BorrowService borrowService;


	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("borrows", borrowService.findAll());
		return "home";
	}
	
	
}

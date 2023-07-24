package com.example.Biblioteka.controller;

import com.example.Biblioteka.service.Borrow.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

package com.example.Biblioteka.controller;

import java.util.List;
import java.util.Set;

import com.example.Biblioteka.service.Users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Biblioteka.entity.*;
import com.example.Biblioteka.repository.*;
import com.example.Biblioteka.service.Borrow.BorrowService;
@Controller
@AllArgsConstructor
public class BorrowController {

	private UserRepository userRepository;
	private BookRepository bookService;
	private BorrowRepository borrowRepository;
	private BorrowService borrowService;
	private RoleRepository roleRepository;

	private UserServiceImpl userService;

	@GetMapping("/home")
	public String home(Model model, @AuthenticationPrincipal UserDetails customUser) {
		Set<Role> role =  userService.findByUsername(customUser.getUsername()).getRoles();

		if(isItUserRole(role)) {

			var id = userService.findByUsername(customUser.getUsername()).getId();

			model.addAttribute("borrows", borrowService.findBorrowForUser(id));

		}else {
			model.addAttribute("borrows", borrowService.findAll());
		}
		return "home";
	}
	@GetMapping("/borrow")
	public String borrowBook(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "borrow";
	}
	@GetMapping("/borrowadd")
	public String borrowBook(@RequestParam("bookId") int bookId,@AuthenticationPrincipal UserDetails customUser) {
		borrowService.reservation(bookId, customUser);
		return "redirect:/borrow";
	}
	@GetMapping("/resignation")
	public String resignation(Model model, @AuthenticationPrincipal UserDetails customUser){
		var user = userService.findUserBySurname(customUser.getUsername());
		Set<Role> role =  user.getRoles();
		if(isItUserRole(role)) {
			var id = user.getId();
			model.addAttribute("borrows", borrowRepository.findBorrowForUserStatus(id, String.valueOf(BorrowStatus.RESERVATION)));
		}else {
			model.addAttribute("borrows", borrowService.findBorrowByStatus("Zarezerwowana"));
		}
		
		return "resignation";
	}
	@GetMapping("/borrowResignation")
	public String resignation(@RequestParam("borrowId") int borrowId) {
		borrowService.cancel(borrowId);
		return "redirect:/home";
	}
	@GetMapping("/release")
	public String release(Model model) {
		List<Borrow> borrow = borrowService.findBorrowByReleaseStatus();
		model.addAttribute("borrows", borrow);
		return "release";
	}
	@PutMapping("/released")
	public String released(Model model, @RequestParam("borrowId") int borrowId) {
		borrowService.releaseBook(borrowId);
		return "redirect:/home";
	}
	@GetMapping("/giveBack")
	public String giveBack(Model model) {
		model.addAttribute("borrows", borrowService.findBorrowByGiveBackStatus());
		return "giveBack";
	}
	@PutMapping("/giveBacks")
	public String giveBack(@RequestParam("borrowId") int borrowId) {
		borrowService.giveBack(borrowId);
		return "redirect:/home";
	}

	public boolean isItUserRole(Set<Role> role)
	{
		return role.contains(roleRepository.findRoleByname("ROLE_USER"));
	}
	
}

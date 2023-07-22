package com.example.Biblioteka.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import com.example.Biblioteka.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Biblioteka.entity.*;
import com.example.Biblioteka.repository.*;
import com.example.Biblioteka.service.BorrowService;
@Controller
@AllArgsConstructor
public class BorrowController {

	private UserRepository userRepository;
	private BookRepository bookService;
	private BorrowRepository borrowRepository;
	private BorrowService borrowService;
	private RoleRepository roleRepository;

	private UserService userService;

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
		Set<Role> role =  (userRepository.findUserByUsername(customUser.getUsername())).getRoles();
		if(isItUserRole(role)) {
			int id = userRepository.findUserByUsername(customUser.getUsername()).getId();
			model.addAttribute("borrows", borrowRepository.findBorrowForUserStatus(id, "Zarezerwowana"));
		}else {
			model.addAttribute("borrows", borrowRepository.findBorrowByStatus("Zarezerwowana"));
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
	@GetMapping("/released")
	public String released(Model model, @RequestParam("borrowId") int borrowId) {
		borrowService.accept(borrowService.findById(borrowId));
		return "redirect:/home";
	}
	@GetMapping("/giveBack")
	public String giveBack(Model model) {
		model.addAttribute("borrows", borrowService.findBorrowByGiveBackStatus());
		return "giveBack";
	}
	@GetMapping("/giveBacks")
	public String giveBack(@RequestParam("borrowId") int borrowId) {
		borrowService.giveBack(borrowId);
		return "redirect:/home";
	}

	public boolean isItUserRole(Set<Role> role)
	{
		return role.contains(roleRepository.findRoleByname("ROLE_USER"))? true : false;
	}
	
}

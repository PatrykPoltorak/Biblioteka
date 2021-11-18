package com.example.Biblioteka.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Biblioteka.entity.User;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.*;

@Controller
public class LoginController {
	private UserRepository userRepository;
	private UserService userService;
	private RoleRepository roleRepository;
	
	public LoginController(UserRepository userRepository, UserService userService,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.roleRepository = roleRepository;
	}
	@GetMapping("/login")
	public String login() {

		return "login";
	}
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
        return "redirect:/login";
    }
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users",roleRepository.findRoleByname("ROLE_USER"));
		model.addAttribute("admin",roleRepository.findAll());
		return "registration";
	}
	@PostMapping("/registration")
	public String registration(@ModelAttribute User user) {
		List<User> tmp = userRepository.findAll();
		for (User users : tmp) {
			String username = users.getUsername();
			if(user.getUsername().equals(username)) {
				System.out.println("już istnieje");
				return"registration";
			}
		}
		userService.add(user);
		return "redirect:/login";
	}	
}

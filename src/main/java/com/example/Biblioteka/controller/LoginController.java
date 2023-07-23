package com.example.Biblioteka.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.example.Biblioteka.entity.Users;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.*;

@Controller
@AllArgsConstructor
public class LoginController {
	private UserRepository userRepository;
	private UserService userService;
	private RoleRepository roleRepository;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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
		model.addAttribute("user", new Users());
		model.addAttribute("users",roleRepository.findRoleByname("ROLE_USER"));
		model.addAttribute("admin",roleRepository.findAll());
		return "registration";
	}
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "registration";
		}else if(userService.userExists(user.getUsername())){
			System.out.println("już istnieje");
			return"registration";
		}else{
			userService.add(user);
			return "redirect:/login";
		}
	}
}


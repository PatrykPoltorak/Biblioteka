package com.example.Biblioteka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.Biblioteka.entity.*;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.serviceInterface.UserInterface;
@Service
public class UserService implements UserInterface {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void add(Users user) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleByname("ROLE_USER"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public void edit(Users user) {
		userRepository.save(user);		
	}
	public Users findByUsername(String name) {
		return userRepository.findUserByUsername(name);
	}
	public List<Users> findAll(){
		return userRepository.findAll();
	}



}

package com.example.Biblioteka.service.Users;

import com.example.Biblioteka.service.Users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.Biblioteka.entity.*;
import com.example.Biblioteka.repository.RoleRepository;
import com.example.Biblioteka.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public void add(Users user) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleByname("ROLE_USER"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);
	}
	@Override

	public boolean userExists(String users){
		for(Users user: userRepository.findAll()){
			if(users.equals(user.getUsername())){
				return true;
			}
		}
		return false;
	}

	@Override
	public void edit(Users user) {
		userRepository.save(user);
	}
	@Override
	public Users findByUsername(String name) {
		return userRepository.findUserByUsername(name);
	}
	@Override
	public List<Users> findAll(){
		return userRepository.findAll();
	}
	@Override
	public Users findUserBySurname(String surname){
		return userRepository.findUserBySurname(surname);
	}
}

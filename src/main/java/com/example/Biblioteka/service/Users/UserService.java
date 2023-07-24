package com.example.Biblioteka.service.Users;

import com.example.Biblioteka.entity.Users;

import java.util.List;

public interface UserService {
	void add(Users user);
	void edit(Users user);
	Users findByUsername(String name);
	List<Users> findAll();
	boolean userExists(String users);
}

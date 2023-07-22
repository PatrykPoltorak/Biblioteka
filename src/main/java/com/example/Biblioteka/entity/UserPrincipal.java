package com.example.Biblioteka.entity;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


@SuppressWarnings("serial")
public class UserPrincipal extends User{
	
	private final Users user;
	
	public UserPrincipal(String username, String password,
			Collection<? extends GrantedAuthority> authorities, Users users) {
		super(username, password, authorities);
		this.user = users;
	}
	
	public Users getUsers() {
		return user;
	}
}

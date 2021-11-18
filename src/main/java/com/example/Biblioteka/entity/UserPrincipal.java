package com.example.Biblioteka.entity;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


@SuppressWarnings("serial")
public class UserPrincipal extends User{
	
	private final  com.example.Biblioteka.entity.User user;
	
	public UserPrincipal(String username, String password,
			Collection<? extends GrantedAuthority> authorities, com.example.Biblioteka.entity.User users) {
		super(username, password, authorities);
		this.user = users;
	}
	
	public com.example.Biblioteka.entity.User getUsers() {
		return user;
	}
}

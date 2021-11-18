package com.example.Biblioteka.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Biblioteka.entity.UserPrincipal;
import com.example.Biblioteka.entity.User;

public class MyUserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;	
	@Autowired
	public void setUsersRepository(UserService usersService) {
		this.userService = usersService;
	}	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User users = userService.findByUsername(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		users.getRoles().forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
		
		System.out.println(users.getRoles() + users.getUsername() + users.getPassword() + grantedAuthorities + users);

		return new UserPrincipal(users.getUsername(),users.getPassword(),grantedAuthorities, users);
		}
}
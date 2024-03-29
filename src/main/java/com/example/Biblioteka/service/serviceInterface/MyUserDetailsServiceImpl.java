package com.example.Biblioteka.service.serviceInterface;

import java.util.HashSet;
import java.util.Set;

import com.example.Biblioteka.service.Users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Biblioteka.entity.UserPrincipal;
import com.example.Biblioteka.entity.Users;

public class MyUserDetailsServiceImpl implements UserDetailsService {

	private UserServiceImpl userService;
	@Autowired
	public void setUsersRepository(UserServiceImpl usersService) {
		this.userService = usersService;
	}	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userService.findByUsername(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		users.getRoles().forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
		
		System.out.println(users.getRoles() + users.getUsername() + users.getPassword() + grantedAuthorities + users);

		return new UserPrincipal(users.getUsername(),users.getPassword(),grantedAuthorities, users);
		}
}
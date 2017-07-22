package com.java.xdd.springsecurity.service.impl;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.java.xdd.springsecurity.domain.User;
import com.java.xdd.springsecurity.domain.UserRole;

public class UserServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		System.out.println(username);
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		Collection<GrantedAuthority> authorities = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setRole("ROLE_ADMIN");
		authorities.add(role);
		user.setAuthorities(authorities);
		return user;
	}

}

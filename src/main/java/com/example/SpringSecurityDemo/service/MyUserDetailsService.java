package com.example.SpringSecurityDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringSecurityDemo.beans.User;
import com.example.SpringSecurityDemo.beans.UserPrincipal;
import com.example.SpringSecurityDemo.dao.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("user 404");
		}
		
		UserPrincipal principle = new UserPrincipal(user);
		return principle;
	}

}

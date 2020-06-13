package com.example.SpringSecurityDemo.security.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Filtering request from Auth filter");
		
		UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
		
		if(userPrincipal != null) {
			System.out.println(userPrincipal.getName());
			System.out.println(userPrincipal.getAuthorities());
		}
	
		/*
		 * while(request.getHeaderNames().hasMoreElements()) {
		 * System.out.println(request.getHeaderNames().nextElement());
		 * request.getHeaderNames().` }
		 */
		
		//request.getSession(false);
		super.doFilter(request, response, filterChain);

	}

}

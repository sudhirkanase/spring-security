package com.example.SpringSecurityDemo.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Security configuration....");
//		http.csrf().disable().authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated()
//				.and().formLogin().and().httpBasic();

		http.authorizeRequests()
		//.antMatchers("/login").permitAll()
		//.antMatchers("/hello").hasRole("EDITOR")
		.anyRequest().authenticated()
		//.anyRequest().authenticated()
		//.and().httpBasic();
		.and().formLogin().and().httpBasic()
		.and().logout().clearAuthentication(true);
		// TODO Auto-generated method stub
		// super.configure(http);
	}

	/**
	 * hardcoded users
	 */
	@Bean
	@SuppressWarnings("deprecation")
	@Override
	protected UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(User.withDefaultPasswordEncoder().username("test").password("test").roles("admin").build());
		return new InMemoryUserDetailsManager(users);
		// return super.userDetailsService();
	}
	
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            //.passwordEncoder(NoOpPasswordEncoder.getInstance());
            .passwordEncoder(passwordEncoder());
    }
	// Either above or below can work..
	/*
	 * @Bean public AuthenticationProvider authProvider() {
	 * 
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setUserDetailsService(userDetailsService);
	 * provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); return
	 * provider;
	 * 
	 * }
	 */

}

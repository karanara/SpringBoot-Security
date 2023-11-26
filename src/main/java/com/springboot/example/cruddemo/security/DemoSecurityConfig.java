package com.springboot.example.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {
	@Bean
	public InMemoryUserDetailsManager userDetailsManager () {
		UserDetails ramya = User.builder()
				    .username("ramya")
				    .password("{noop}test123")
				    .roles("EMPLOYEE")
				    .build();
		UserDetails joshna = User.builder()
			    .username("joshna")
			    .password("{noop}test123")
			    .roles("EMPLOYEE","MANAGER")
			    .build();
		UserDetails baby = User.builder()
			    .username("baby")
			    .password("{noop}test123")
			    .roles("EMPLOYEE","MANAGER","ADMIN")
			    .build();
		return new InMemoryUserDetailsManager(ramya,joshna,baby);
	}
}
//spring use this usernams and password instead of details given in application properties
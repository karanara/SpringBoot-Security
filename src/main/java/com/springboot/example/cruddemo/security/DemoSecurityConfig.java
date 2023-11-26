package com.springboot.example.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	//Basic Configuration
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
	//spring use this username and password instead of details given in application properties

	// Restrict url endpoints based on Roles

	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer->
		      configurer
		      .requestMatchers(HttpMethod.GET,"/api/employee").hasRole("EMPLOYEE")
		      .requestMatchers(HttpMethod.GET,"/api/employee/**").hasRole("EMPLOYEE")
              .requestMatchers(HttpMethod.POST,"/api/employee").hasRole("MANAGER")
              .requestMatchers(HttpMethod.PUT,"/api/employee").hasRole("MANAGER")
              .requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("ADMIN")
				);
		//use Basic Http Authorization
		http.httpBasic(Customizer.withDefaults());
		//disable cross site Request Forgery
		//in general not used for stateless REst API that use POST ,PUT,GET,DELETE methods
		http.csrf(csrf->csrf.disable());
		
		return http.build();
	}
}

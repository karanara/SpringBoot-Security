package com.springboot.example.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	/*
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
	//spring use this username and password instead of details given in application properties*/
	
	//using JDBC support for authentication no more hard coding for username and passwords
	
	  @Bean
	  public UserDetailsManager userDetailsManager(DataSource dataSource) {
		//return new JdbcUserDetailsManager(dataSource);
		  //custom scheema which matches nothing to spring security table schema
		  JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		  //define query to retreive a user by username
		  
		  jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
		  
		  //define query to retreive the authorities/roles by username
		  jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
		  
		  return jdbcUserDetailsManager;
	  }

	// Restrict url endpoints based on Roles
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer->
		      configurer
		      .requestMatchers(HttpMethod.GET,"/api/employee").hasRole("EMPLOYEE")
		      .requestMatchers(HttpMethod.GET,"/api/employee/**").hasRole("EMPLOYEE")
              .requestMatchers(HttpMethod.POST,"/api/employee").hasRole("MANAGER")
              .requestMatchers(HttpMethod.PUT,"/api/employee").hasRole("MANAGER")
              .requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("ADMIN")
				);
		//use Basic Http Authentication
		http.httpBasic(Customizer.withDefaults());
		//disable cross site Request Forgery
		//in general not used for stateless REst API that use POST ,PUT,GET,DELETE methods
		http.csrf(csrf->csrf.disable());
		
		return http.build();
	}
}

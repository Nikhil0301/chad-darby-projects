package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	
	
	@Bean //Inject the datasource ,and this datasource is auto configured by springboot
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		//define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id , pw , active FROM members where user_id = ?");
		
		//define a query to retrieve the authorities/roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id , role from roles where user_id = ?");
		
		return jdbcUserDetailsManager;//It tells spring security to use JDBC authentication with our datasource
	}
	
	/*@Bean //Inject the datasource ,and this datasource is auto configured by springboot
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		return new JdbcUserDetailsManager(dataSource);//It tells spring security to use JDBC authentication with our datasource
	}*/
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer ->
		configurer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
		          .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
		          .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
		          .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
		          .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				);
		
		// we are telling spring security that we are using basic authentication
		http.httpBasic();
		
		//Disable cross site request Forgery (CSRF)
		//In general . not required for state less REST APIs that use POST ,PUT, DELETE and /or PATCH
		http.csrf().disable();
		
		return http.build();
	}
	
	
	//{noop} plain text passwords
		//bcrypt Bcrypt passwprd hashing
		
		
		
		//Restricting access based on roles
/*   @Bean
	public InMemoryUserDetailsManager userDetailManager() {
		UserDetails john = User.builder().
				           username("jhon").
				           password("{noop}test123").
				           roles("EMPLOYEE").
				           build();
		
		UserDetails mary = User.builder().
		           username("mary").
		           password("{noop}test123").
		           roles("EMPLOYEE","MANAGER").
		           build();
		
		UserDetails susan = User.builder().
		           username("susan").
		           password("{noop}test123").
		           roles("EMPLOYEE","MANAGER","ADMIN").
		           build();
		return new InMemoryUserDetailsManager(john,mary,susan);
	}
*/
}

package io.msiongoog.filefishing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import io.msiongoog.filefishing.services.UserDetailsServiceImpl;
import io.msiongoog.filefishing.utils.PasswordUtils;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected  static final String USER = "USER";
	protected static final String ADMIN = "ADMIN";
	public static final String UPLOADER = "UPLOADER";

	
	protected static final String BASIC_AUTH_REALM = "shyam_basic_auth";
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		//below code also works: v0.3
		//only one works at a time. v0.3 - best, easy to incorporate DB when required
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder());

		//below code also works; v0.2
	//	auth.userDetailsService(userDetailsService()).passwordEncoder(bcryptPasswordEncoder());
		
		//below code works; v0.1
//		auth.inMemoryAuthentication()
//		.withUser("shyam").password("$2a$10$4tXhsxtJ1E5SbfI5EnShuudMxYxPt8aOD7SweCbpopLLHindLwIdS").roles(USER).and()
//		.withUser("raman").password("$2a$10$4tXhsxtJ1E5SbfI5EnShuudMxYxPt8aOD7SweCbpopLLHindLwIdS").roles(ADMIN)
//		;
	}
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("ravi").password(PasswordUtils.BCryptpassword("password")).roles(USER).build());
        manager.createUser(User.withUsername("shyama").password(PasswordUtils.BCryptpassword("password")).roles(ADMIN).build());
        return manager;
	}


	@Bean
	@Autowired
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	





  

}

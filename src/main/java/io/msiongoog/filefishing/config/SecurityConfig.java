package io.msiongoog.filefishing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String USER = "USER";
	private static final String ADMIN = "ADMIN";
	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("shyam").password("$2a$10$4tXhsxtJ1E5SbfI5EnShuudMxYxPt8aOD7SweCbpopLLHindLwIdS").roles(USER).and()
		.withUser("raman").password("$2a$10$4tXhsxtJ1E5SbfI5EnShuudMxYxPt8aOD7SweCbpopLLHindLwIdS").roles(ADMIN)
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/protected/user/**").hasRole(USER)
		.antMatchers(HttpMethod.GET,"/protected/admin/**").hasRole(ADMIN)
		.and().httpBasic()
		.and()
		.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}

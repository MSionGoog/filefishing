package io.msiongoog.filefishing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class Oauth2ResourceServer extends ResourceServerConfigurerAdapter{


	@Autowired
	BasicAuthenticationEntryPointImpl basicAuthenticationEntryPoint;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
		.authorizeRequests().antMatchers("/oauth2protected/**").fullyAuthenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/protected/user/**").hasRole(SecurityConfig.USER)
		.antMatchers(HttpMethod.GET,"/protected/admin/**").hasRole(SecurityConfig.ADMIN)
		.and().httpBasic().realmName(SecurityConfig.BASIC_AUTH_REALM)
		.authenticationEntryPoint(basicAuthenticationEntryPoint)
		.and()
		.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        
        ;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("oauth2-resource");
	}


	
	
}

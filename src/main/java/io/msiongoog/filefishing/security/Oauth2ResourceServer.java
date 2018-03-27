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

	public static final String OAUTH2PROTECTED = "/oauth2protected";
	public static final String BASICAUTHPROTECTED = "/protected";

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
		.antMatchers(HttpMethod.GET, "/protected/files/**").hasRole(SecurityConfig.UPLOADER)
		.antMatchers(HttpMethod.POST, "/protected/files/**").hasRole(SecurityConfig.UPLOADER)
		.antMatchers(HttpMethod.GET, "/protected/user/**").hasRole(SecurityConfig.USER)
		.antMatchers(HttpMethod.GET,"/protected/admin/**").hasRole(SecurityConfig.ADMIN)
		.antMatchers(HttpMethod.GET,"/protected/dropbox/**").hasRole(SecurityConfig.ADMIN)
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

package io.msiongoog.filefishing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import io.msiongoog.filefishing.services.UserDetailsServiceImpl;

@Order(SecurityProperties.BASIC_AUTH_ORDER-10)
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
    protected static String REALM="shyam_oauth2";

    
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;
	
	private int expiration = 3600;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("oauth2shyam").secret("$2a$10$Dyyr7.lEQe2S2Eb2rn1xVefHcLgzoK0m3N3JGkxB18SLXLKlI8DEC").accessTokenValiditySeconds(expiration)
		.scopes("read","write").authorizedGrantTypes("client_credentials").resourceIds("oauth2-resource");
		

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.realm(REALM);
	}
	
	
	
	
}

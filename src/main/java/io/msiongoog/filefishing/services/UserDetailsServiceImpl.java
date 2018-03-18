package io.msiongoog.filefishing.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import io.msiongoog.filefishing.domains.Account;
import io.msiongoog.filefishing.exceptions.NoAccountFoundException;
import io.msiongoog.filefishing.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("UserDetailsServiceImpl.loadUserByUsername:: in method");
		
		Account account = userRepository.findByUsername(username);
		log.info("UserDetailsServiceImpl.loadUserByUsername:: account details fetched " );
		
		
		if(account != null) {
			log.info("UserDetailsServiceImpl.loadUserByUsername:: account is not null");

			log.info("UserDetailsServiceImpl.loadUserByUsername:: " + account.toString());

			return new User(account.getUsername(), account.getPassword(),
					true, true, true, true,
					AuthorityUtils.createAuthorityList(account.getRoles()
							.toArray(new String[account.getRoles().size()])));
		}
		else
		{
			log.info("UserDetailsServiceImpl.loadUserByUsername:: ");

			throw new NoAccountFoundException("UserDetailsServiceImpl :: Account found but repository returns null");
		}
		
	}


	
	

}

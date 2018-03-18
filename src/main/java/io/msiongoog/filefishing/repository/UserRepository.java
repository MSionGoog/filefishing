package io.msiongoog.filefishing.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.msiongoog.filefishing.domains.Account;
import io.msiongoog.filefishing.exceptions.NoAccountFoundException;
import io.msiongoog.filefishing.utils.PasswordUtils;


@Repository
public class UserRepository {
	Map<String,Account> accounts;
	
	public UserRepository() {


		accounts = new HashMap<>();
		
		Account accountUser = new Account();
		accountUser.setUsername("sooraj");
		accountUser.setPassword(PasswordUtils.BCryptpassword("password"));
		accountUser.setRoles(new HashSet<String>() {

			private static final long serialVersionUID = 1L;

			{
				add("ROLE_USER");
			}
		});
		
		Account accountAdmin = new Account();
		accountAdmin.setUsername("arvind");
		accountAdmin.setPassword(PasswordUtils.BCryptpassword("password"));
		accountAdmin.setRoles(new HashSet<String>() {
			
			private static final long serialVersionUID = 6894494850559401611L;

			{
				add("ROLE_USER");
				add("ROLE_ADMIN");
			}
		});
		
		Account accountUploader = new Account();
		accountUser.setUsername("shyam");
		accountUser.setPassword(PasswordUtils.BCryptpassword("password"));
		accountUser.setRoles(new HashSet<String>() {

			private static final long serialVersionUID = 1L;

			{
				add("ROLE_UPLOADER");
			}
		});
		
		accounts.put(accountAdmin.getUsername(), accountAdmin);
		accounts.put(accountUser.getUsername(), accountUser);
		accounts.put(accountUploader.getUsername(), accountUploader);
	}
	
	 public Account findByUsername(String username) throws NoAccountFoundException  {
		
		
		if(accounts.containsKey(username)) {
			return accounts.get(username);
		}
		else
		{
			throw new NoAccountFoundException(" UserRepository :: Username not found");
		}
	}

}

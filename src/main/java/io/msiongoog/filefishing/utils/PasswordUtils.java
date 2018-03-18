package io.msiongoog.filefishing.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	

	public static String BCryptpassword(String password) {
		
		String passwordEncoded = new BCryptPasswordEncoder().encode(password);
		return passwordEncoded;
	}

}

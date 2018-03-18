package io.msiongoog.filefishing.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	

	public static void runThis() {
		
		String passwordEncoded = new BCryptPasswordEncoder().encode("password");
		System.out.println(passwordEncoded);
	}

}

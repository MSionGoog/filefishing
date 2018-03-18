package io.msiongoog.filefishing.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.msiongoog.filefishing.domains.HttpMessage;

@RestController
@RequestMapping(path="/protected")
public class InitResource {

	private static final String PROTECTED_USER_SUCCESSFULLY_AUTHENTICATED = "/protected/user successfully authenticated";
	private static final String PROTECTED_ADMIN_SUCCESSFULLY_AUTHENTICATED = "/protected/admin successfully authenticated";

	@GetMapping(path="/admin")
	public ResponseEntity<HttpMessage> defaultResource() {
		String responseString = new String(PROTECTED_ADMIN_SUCCESSFULLY_AUTHENTICATED);
		return  new ResponseEntity<HttpMessage>(new HttpMessage(responseString)
				, HttpStatus.OK);
	}
	
	@GetMapping(path="/user")
	public ResponseEntity<HttpMessage> testResponseResource() {
		String responseString = new String(PROTECTED_USER_SUCCESSFULLY_AUTHENTICATED);
		return  new ResponseEntity<HttpMessage>(new HttpMessage(responseString)
				, HttpStatus.OK);
	}
}

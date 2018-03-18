package io.msiongoog.filefishing.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/protected")
public class InitResource {

	@GetMapping(path="/admin")
	public ResponseEntity<String> defaultResource() {
		String responseString = new String("/private/admin successfully authenticated");
		return  new ResponseEntity<String>(responseString
				, HttpStatus.OK);
	}
	
	@GetMapping(path="/user")
	public ResponseEntity<String> testResponseResource() {
		String responseString = new String("/private/user successfully authenticated");
		return  new ResponseEntity<String>(responseString
				, HttpStatus.OK);
	}
}

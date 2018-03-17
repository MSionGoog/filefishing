package io.msiongoog.filefishing.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitResource {

	@GetMapping(path="/")
	public String defaultResource() {
		
		return new String("File handling application where one can, through a browser client,  upload files and chose where one wants to store it and if the location has been integrated, will be used. ");
	}
}

package io.msiongoog.filefishing.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitResource {

	@GetMapping(path="/")
	public String defaultResource() {
		
		return new String("hello world");
	}
}

package io.msiongoog.filefishing.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.msiongoog.filefishing.domains.HttpMessage;
import io.msiongoog.filefishing.exceptions.NoAccountFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{

	
	
	
	@ExceptionHandler(value=NoAccountFoundException.class)
	protected ResponseEntity<HttpMessage> handleException(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		
		return new ResponseEntity<HttpMessage>(new HttpMessage(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}

package io.msiongoog.filefishing.exceptions;
/**
 * TODO Auto-generated Exception
 */
 public class NoAccountFoundException extends RuntimeException {
	 /**
	  * TODO Auto-generated Default Serial Version UID
	  */
	 private static final long serialVersionUID = 1L;    
	 
	 /**
	  * @see Exception#Exception()
	  */
	 public NoAccountFoundException() {
		 super();
	 }
	 
	 /**
	  * @see Exception#Exception(String) 
	  */
	 public NoAccountFoundException(String message) {
		 super(message);         
	 }
	 
	 /**
	  * @see Exception#Exception(Throwable)
	  */
	 public NoAccountFoundException(Throwable cause) {
		 super(cause);           
	 }
	 
	 /**
	  * @see Exception#Exception(String, Throwable)
	  */
	 public NoAccountFoundException(String message, Throwable cause) {
		 super(message, cause);
	 }
 }
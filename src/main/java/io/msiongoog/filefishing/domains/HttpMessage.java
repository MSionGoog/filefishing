package io.msiongoog.filefishing.domains;

public class HttpMessage {
	
	private Object message;

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public HttpMessage(Object message) {
		super();
		this.message = message;
	}
	
	public HttpMessage() {
		// TODO Auto-generated constructor stub
	}

}

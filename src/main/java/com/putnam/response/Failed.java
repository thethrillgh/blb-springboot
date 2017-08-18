package com.putnam.response;

public class Failed {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Failed(String message) {
		super();
		this.message = message;
	}
	
}

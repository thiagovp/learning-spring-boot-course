package com.veloso.learningspringboot.resource;

class ErrorMessage {
	String errorMessage;

	public ErrorMessage(String message) {
		super();
		this.errorMessage = message;
	}

	public String getMessage() {
		return errorMessage;
	}

	public void setMessage(String message) {
		this.errorMessage = message;
	}

}

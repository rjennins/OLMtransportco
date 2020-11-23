package com.olmtransportco.ships.exception;

public class OLMtransportcoException extends Exception {
	private static final long serialVersionUID = 4445663325366652922L;
	private String message;
	private Exception exception;

	public OLMtransportcoException(String message) {
		this.message = message;
	}

	public OLMtransportcoException(String message, Exception e) {
		this.message = message;
		this.exception = e;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}

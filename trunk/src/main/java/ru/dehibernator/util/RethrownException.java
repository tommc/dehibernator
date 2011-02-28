package ru.dehibernator.util;

public class RethrownException extends RuntimeException {
	private static final long serialVersionUID = 6179002240028760762L;

	@SuppressWarnings("unused")
	private RethrownException() {}

	RethrownException(Exception exception) {
		super(exception);
	}

	public Exception getException() {
		return (Exception) getCause();
	}
}

package com.flyhub.ideamanagementsystem.exception;

public class IdeaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public IdeaNotFoundException(String message) {
		super(message);
	}
}

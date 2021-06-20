package com.flyhub.ideamanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flyhub.ideamanagementsystem.exception.UserAlreadyExistsException;
import com.flyhub.ideamanagementsystem.exception.UserNotFoundException;


@RestController
@RestControllerAdvice

public class ExceptionsController {	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUsernotFoundException(UserNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
	}
}

package com.flyhub.ideamanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flyhub.ideamanagementsystem.exception.CategoryNotFoundException;
import com.flyhub.ideamanagementsystem.exception.CountryNotFoundException;
import com.flyhub.ideamanagementsystem.exception.GenderNotFoundException;
import com.flyhub.ideamanagementsystem.exception.IdeaNotFoundException;
import com.flyhub.ideamanagementsystem.exception.PostfixNotFoundException;
import com.flyhub.ideamanagementsystem.exception.PrefixNotFoundException;
import com.flyhub.ideamanagementsystem.exception.PriorityNotFoundException;
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
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(GenderNotFoundException.class)
	public ResponseEntity<String> handleGenderNotFoundException(GenderNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<String> handleCountryNotFoundException(CountryNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PrefixNotFoundException.class)
	public ResponseEntity<String> handlePrefixNotFoundException(PrefixNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostfixNotFoundException.class)
	public ResponseEntity<String> handlePostfixNotFoundException(PostfixNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdeaNotFoundException.class)
	public ResponseEntity<String> handleIdeaNotFoundException(IdeaNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PriorityNotFoundException.class)
	public ResponseEntity<String> handlePriorityNotFoundException(PriorityNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

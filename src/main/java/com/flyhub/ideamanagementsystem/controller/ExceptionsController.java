package com.flyhub.ideamanagementsystem.controller;
import javax.persistence.EntityNotFoundException;

import org.hibernate.PropertyValueException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.exceptions.TemplateInputException;

import com.flyhub.ideamanagementsystem.exception.ApiException;

@Controller
@ControllerAdvice
public class ExceptionsController {		
	 @ExceptionHandler(EntityNotFoundException.class)
	public String handleEntityNotFoundException(EntityNotFoundException ex,WebRequest request,Model model) {
		ApiException exception = new ApiException(ex.getMessage(), request.getDescription(false),ex.getClass().getSimpleName());
		model.addAttribute("message",exception.getMessage());
		model.addAttribute("path",exception.getPath());
		model.addAttribute("errorName",exception.getErrorName());
		return "errors";
	} 
	 
	 @ExceptionHandler(PropertyValueException.class)
		public String handlePropertyValueException(PropertyValueException ex,WebRequest request,Model model) {
			ApiException exception = new ApiException(ex.getMessage(), request.getDescription(false),ex.getClass().getSimpleName());
			model.addAttribute("message",exception.getMessage());
			model.addAttribute("path",exception.getPath());
			model.addAttribute("errorName",exception.getErrorName());
			return "errors";
		}
	 
	 @ExceptionHandler( PropertyReferenceException.class)
		public String handlePropertyReferenceException( PropertyReferenceException ex,WebRequest request,Model model) {
			ApiException exception = new ApiException(ex.getMessage(), request.getDescription(false),ex.getClass().getSimpleName());
			model.addAttribute("message",exception.getMessage());
			model.addAttribute("path",exception.getPath());
			model.addAttribute("errorName",exception.getErrorName());
			return "errors";
		}
	 
	 @ExceptionHandler(TemplateInputException.class)
		public String handleTemplateInputException( TemplateInputException ex,WebRequest request,Model model) {
			ApiException exception = new ApiException(ex.getMessage(), request.getDescription(false),ex.getClass().getSimpleName());
			model.addAttribute("message",exception.getMessage());
			model.addAttribute("path",exception.getPath());
			model.addAttribute("errorName",exception.getErrorName());
			return "errors";
		}

}
 
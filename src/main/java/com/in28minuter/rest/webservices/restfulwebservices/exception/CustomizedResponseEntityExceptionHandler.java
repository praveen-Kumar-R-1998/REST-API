package com.in28minuter.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minuter.rest.webservices.restfulwebservices.user.UserNotFoundException;

/**
 * ResponseEntityExceptionHandeler is the default class that handles all the
 * Spring MVC raised exception and it returns formatted error details.
 * 
 * Their are many methods to handle different exception in this class but the
 * fundamental one is "handleException(Exception,WebRequest)" this is where the
 * standard structure (exception JSON format)is coming in from.
 */

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @ExceptionHandler this annotation decides what type of exception to handle
	 */

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}

package com.in28minuter.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author 
 * Hear we are creating a custom exception called "UserNotFoundException" which extends RuntimeException
 * and by default it will send HTTP 500 error when the user is not found but to send a a different HTTP status
 * in this case 404 user not found we will be using the annotation "@ResponseStatus(code = HttpStatus.NOT_FOUND)"
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
}

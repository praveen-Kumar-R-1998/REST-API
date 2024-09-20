package com.in28minuter.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

//	Not using @Autowired annotation hear injecting the UserDaoService instance using constructor injection.
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> reteriveAllUser() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User reteriveSpecificUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user==null) {
			throw new UserNotFoundException("id: "+id);
		}
		return service.findOne(id);
		
	}

	/**
	 * 
	 * @param user
	 * @return This method will return the status code 201 if the user is added
	 *         successfully and we are also sending the location of the user which
	 *         is created.
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}

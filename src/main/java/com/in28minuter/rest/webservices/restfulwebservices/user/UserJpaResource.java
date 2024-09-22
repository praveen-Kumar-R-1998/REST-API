package com.in28minuter.rest.webservices.restfulwebservices.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minuter.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

//	Not using @Autowired annotation hear injecting the UserDaoService instance using constructor injection.

	
	private UserRepository repository;

	public UserJpaResource(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser() {
		return repository.findAll();
	}
	
	/**
	 * Hear we are warping the user class and creating an entity model
	 */

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> reteriveSpecificUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-users"));
		
//		return service.findOne(id);
		return entityModel;

	}

	/**
	 * 
	 * @param user
	 * @return This method will return the status code 201 if the user is added
	 *         successfully and we are also sending the location of the user which
	 *         is created.
	 */
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteSpecificUser(@PathVariable int id) {
		repository.deleteById(id);
	}

}

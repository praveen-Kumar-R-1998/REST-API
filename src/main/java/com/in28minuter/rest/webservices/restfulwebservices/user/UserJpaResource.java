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

import com.in28minuter.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minuter.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

//	Not using @Autowired annotation hear injecting the UserDaoService instance using constructor injection.

	private UserRepository userRepository;
	
	private PostRepository postRepository;
	
	
//	private UserRepository repository;
	
	

	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
		super();
		this.userRepository = repository;
		this.postRepository = postRepository;
	}
	
	

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser() {
		return userRepository.findAll();
	}
	
	

	/**
	 * Hear we are warping the user class and creating an entity model
	 */

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> reteriveSpecificUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

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
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	

	@DeleteMapping("/jpa/users/{id}")
	public void deleteSpecificUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		System.out.println("User for which post has to be retrived is: "+user);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		System.out.println("posts are: "+user.get().getPosts());
		return user.get().getPosts();

	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/jpa/users/{id}/posts").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}

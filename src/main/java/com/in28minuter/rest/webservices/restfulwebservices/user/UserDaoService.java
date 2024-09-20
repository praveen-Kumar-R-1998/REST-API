package com.in28minuter.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCount=0; 
	static {
		users.add(new User(++userCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Eve",LocalDate.now().minusYears(27)));
		users.add(new User(++userCount,"John",LocalDate.now().minusYears(24)));
		users.add(new User(++userCount,"William",LocalDate.now().minusYears(21)));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		
		java.util.function.Predicate<User> predicate = user->user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	
}

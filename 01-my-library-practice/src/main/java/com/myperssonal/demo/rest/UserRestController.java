package com.myperssonal.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myperssonal.demo.entity.User;
import com.myperssonal.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	UserService userService;

	@Autowired
	public UserRestController(UserService theUserService) {
		userService = theUserService;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User theUser = userService.getUserById(userId);
		if (theUser == null) {
			throw new CustomerNotFoundException("User not founded for id: " + userId);
		}
		return theUser;
	}

	@PostMapping("/users/")
	public User addUser(@RequestBody User theUser) {
		theUser.setId(0);
		if (theUser.getName() == null || theUser.getPassword()==null || theUser.getRole()==null) {
			throw new RuntimeException("User must have a name.password and role!!!");
		}
		userService.saveUser(theUser);
		return theUser;
	}

	@PutMapping("/users")
	public User putUser(@RequestBody User theUser) {

		userService.saveUser(theUser);
		return theUser;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User tempUser = userService.getUserById(userId);

		if (tempUser == null) {
			throw new CustomerNotFoundException("User not founded for id: " + userId);
		}
		userService.deleteUser(userId);
		return "Deleted user id-" + userId;
	}

	@GetMapping("/usersbyname/{name}")
	public List<User> getUserByName(@PathVariable String name) {
		List<User> theUsers = userService.getUsersByName(name);
		if (theUsers.size() == 0) {
			throw new RuntimeException("User not founded for name: " + name);
		}
		return theUsers;
	}
}
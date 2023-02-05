package es.molina.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.molina.springboot.entity.User;
import es.molina.springboot.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// GET
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		final User createdUser = this.userService.getUserById(id);
		
		return ResponseEntity.ok(createdUser);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		final List<User> users = this.userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
	
	// POST
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		final User createdUser = this.userService.createUser(user);
		
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}
	
	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		final User updatedUser = this.userService.updateUser(id, user);
		
		return ResponseEntity.ok(updatedUser);
	}
}

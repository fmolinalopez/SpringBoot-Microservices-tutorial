package es.molina.springboot.controller;

import java.net.http.HttpResponse;
import java.util.List;

import es.molina.springboot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		final UserDto createdUser = this.userService.getUserById(id);
		
		return ResponseEntity.ok(createdUser);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		final List<UserDto> users = this.userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
	
	// POST
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		final UserDto createdUser = this.userService.createUser(user);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
		final UserDto updatedUser = this.userService.updateUser(id, user);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}

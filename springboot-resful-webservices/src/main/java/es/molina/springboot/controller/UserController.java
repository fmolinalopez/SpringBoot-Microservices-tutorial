package es.molina.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import es.molina.springboot.dto.UserDto;
import es.molina.springboot.exception.ErrorDetails;
import es.molina.springboot.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.molina.springboot.entity.User;
import es.molina.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.context.request.WebRequest;

@Tag(
		name = "CRUD REST APIs for User Resource",
		description = "CRUD REST API Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// GET
	@Operation(
			summary = "Get User By ID REST API",
			description = "Get User By ID REST API is used to get a single user from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		final UserDto createdUser = this.userService.getUserById(id);
		
		return ResponseEntity.ok(createdUser);
	}

	@Operation(
			summary = "Get All Users REST API",
			description = "Get All Users REST API is used to get all users from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		final List<UserDto> users = this.userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
	
	// POST
	@Operation(
			summary = "Create User REST API",
			description = "Create User REST API is used to save user in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		final UserDto createdUser = this.userService.createUser(user);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	// PUT
	@Operation(
			summary = "Update User REST API",
			description = "Update User REST API is used to update a particular user in the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto user) {
		final UserDto updatedUser = this.userService.updateUser(id, user);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	// DELETE
	@Operation(
			summary = "Delete User REST API",
			description = "Delete User REST API is used to delete a particular user in the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}

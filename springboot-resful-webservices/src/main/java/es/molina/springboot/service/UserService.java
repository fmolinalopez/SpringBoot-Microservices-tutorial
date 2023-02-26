package es.molina.springboot.service;

import java.util.List;

import es.molina.springboot.dto.UserDto;
import es.molina.springboot.entity.User;

public interface UserService {
	
	UserDto createUser(UserDto user);

	UserDto getUserById(Long id);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(Long id, UserDto user);
	
	void deleteUser(Long id);
}

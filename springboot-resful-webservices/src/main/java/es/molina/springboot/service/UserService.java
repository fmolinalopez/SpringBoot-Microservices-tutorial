package es.molina.springboot.service;

import java.util.List;

import es.molina.springboot.entity.User;

public interface UserService {
	
	User createUser(User user);

	User getUserById(Long id);
	
	List<User> getAllUsers();
	
	User updateUser(Long id, User user);
	
	void deleteUser(Long id);
}

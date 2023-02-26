package es.molina.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.molina.springboot.entity.User;
import es.molina.springboot.repository.UserRepository;
import es.molina.springboot.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> optionalUser =  this.userRepository.findById(id);
		
		return optionalUser.get();
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User updateUser(Long id, User user) {
		final Optional<User> userToUpdate = this.userRepository.findById(id);
		
		return this.userRepository.save(userToUpdate.get()
				.toBuilder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build());
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}

}

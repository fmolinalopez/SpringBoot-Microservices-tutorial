package es.molina.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import es.molina.springboot.dto.UserDto;
import es.molina.springboot.exception.EmailAlreadyExistsException;
import es.molina.springboot.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import es.molina.springboot.entity.User;
import es.molina.springboot.repository.UserRepository;
import es.molina.springboot.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email is already in use");
		}

		// Convert UserDto to User Jpa Entity
		// User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);

		User savedUser = this.userRepository.save(user);

		// Convert User Jpa Entity to UserDto
		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
		User user =  this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

//		return UserMapper.mapToUserDto(optionalUser.get());
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return this.userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UserDto user) {

		final User userToUpdate = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		User updatedUser = this.userRepository.save(userToUpdate
				.toBuilder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build());

		return modelMapper.map(updatedUser, UserDto.class);
//		return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

		this.userRepository.deleteById(id);
	}

}

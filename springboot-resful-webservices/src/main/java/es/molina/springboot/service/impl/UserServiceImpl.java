package es.molina.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.molina.springboot.dto.UserDto;
import es.molina.springboot.mapper.AutoUserMapper;
import es.molina.springboot.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

		// Convert UserDto to User Jpa Entity
		// User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);
		// User user = AutoUserMapper.MAPPER.mapToUser(userDto);

		User savedUser = this.userRepository.save(user);

		// Convert User Jpa Entity to UserDto
		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		// UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<User> optionalUser =  this.userRepository.findById(id);

//		return UserMapper.mapToUserDto(optionalUser.get());
		return modelMapper.map(optionalUser.get(), UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return this.userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UserDto user) {
		final Optional<User> userToUpdate = this.userRepository.findById(id);
		
		User updatedUser = this.userRepository.save(userToUpdate.get()
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
		this.userRepository.deleteById(id);
	}

}

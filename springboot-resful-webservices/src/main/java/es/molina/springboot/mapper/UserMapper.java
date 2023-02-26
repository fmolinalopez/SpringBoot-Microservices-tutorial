package es.molina.springboot.mapper;

import es.molina.springboot.dto.UserDto;
import es.molina.springboot.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}

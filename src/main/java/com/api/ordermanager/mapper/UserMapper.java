package com.api.ordermanager.mapper;

import java.util.Objects;

import com.api.ordermanager.dto.UserDto;
import com.api.ordermanager.model.User;

public class UserMapper {

	public static User mapToEntity(UserDto userDto) {

		if (Objects.isNull(userDto)) {
			return null;
		}

		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		return user;

	}
}

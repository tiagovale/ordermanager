package com.api.ordermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.ordermanager.dto.UserDto;
import com.api.ordermanager.mapper.UserMapper;
import com.api.ordermanager.model.User;
import com.api.ordermanager.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private  UserRepository repository;

	public List<User> getAll() {
		return repository.findAll();
	}

	@Transactional
	public User create(UserDto userDto) {

		try {
			User user = UserMapper.mapToEntity(userDto);
			return repository.save(user);
		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

		return null;

	}
	
	public void update(Long id, UserDto userDto) throws Exception {

		User user = repository.findById(id).orElseThrow(() -> new Exception("User not found"));

		User userToUpdate = UserMapper.mapToEntity(userDto);
		userToUpdate.setId(user.getId());
		repository.save(userToUpdate);

	}

	@Transactional
	public void delete(Long userId) {
		try {
			Optional<User> user = repository.findById(userId);

			if (user.isPresent()) {
				repository.delete(user.get());
			}

		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

	}
}

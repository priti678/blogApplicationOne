package com.codewithme.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithme.blog.entities.User;
import com.codewithme.blog.payload.UserDto;

@Service
public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void DeleteUser(Integer userId);
//	public User UserDtoToUser(UserDto userDto);
//	public UserDto UserToUserDto(User user);
}
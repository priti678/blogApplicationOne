package com.codewithme.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithme.blog.entities.User;
import com.codewithme.blog.exception.*;
import com.codewithme.blog.payload.UserDto;
import com.codewithme.blog.repositories.UserRepo;
import com.codewithme.blog.service.UserService;


@Service
class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	
	@Autowired
	public ModelMapper modelmapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.UserDtoToUser(userDto);
		User userdto = this.userRepo.save(user);
		return  this.UserToUserDto(userdto);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		User mainUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" , "id", userId));
		mainUser.setName(user.getName());
		mainUser.setEmail(user.getEmail());
		mainUser.setAbout(user.getAbout());
		mainUser.setPassword(user.getPassword());
		User UpdateUser = this.userRepo.save(mainUser);
		return this.UserToUserDto(UpdateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user " , "id", userId));;
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> user = this.userRepo.findAll();
		List<UserDto> userDto = user.stream().map(users -> this.UserToUserDto(users)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void DeleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" , "id", userId));;
		this.userRepo.delete(user);
	}
	
	public User UserDtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		User user = this.modelmapper.map(userDto, User.class);
		return user;
	}

	public UserDto UserToUserDto(User user) {
		UserDto userDto = this.modelmapper.map(user, UserDto.class);
		return userDto;
	}

}
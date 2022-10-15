package com.codewithme.blog.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codewithme.blog.payload.ApiResponce;
import com.codewithme.blog.payload.UserDto;
import com.codewithme.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	public UserService userservice;

//         creating the user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @RequestBody @Valid UserDto userdto) {
		UserDto userDt = this.userservice.createUser(userdto);
		return new ResponseEntity<>(userDt, HttpStatus.CREATED);
	}

//         updating the user
	@PutMapping("/{useID}")
	public ResponseEntity<UserDto> updateUser(@RequestBody @Valid  UserDto userdto, @PathVariable("userID") Integer id) {
		UserDto userdto1 = this.userservice.updateUser(userdto, id);
		return new ResponseEntity<>(userdto1, HttpStatus.OK);
	}

// detete user
	@DeleteMapping("/{userID}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userID") Integer id) {
		this.userservice.DeleteUser(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted successfully", true), HttpStatus.OK);
	}

//        get particular user
	@GetMapping("/{userID}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userID") Integer userId) {
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}

//  get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userservice.getAllUser());
	}
}
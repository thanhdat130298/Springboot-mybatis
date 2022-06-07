package com.example.SpringMybatisXml.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Users.ItemUserDTO;
import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Models.Users.UsersDTO;
import com.example.SpringMybatisXml.Services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService UserService;

	// GET ALL
	@GetMapping()
	public UsersDTO getAllUsers(@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "rowSize", required = false, defaultValue = "10") int rowSize) {
		return UserService.getAllUsers(sortBy, pageNo, rowSize);
	}

	// GET USER BY USERNAME
	@GetMapping("/{userId}")
	public ItemUserDTO getUserById(@PathVariable int userId) {
		return UserService.getUserById(userId);
	}

	// create user
	@PostMapping()
	public NotifyModel createUser(@Valid @RequestBody UserModel user, Errors errors) {
		return UserService.createUser(user, errors);
	}

	// update user rest api
	@PutMapping("/{userId}")
	public NotifyModel updateUser(@PathVariable int userId, @RequestBody UserModel userDetails, Errors errors) {
		return UserService.updateUser(userId, userDetails, errors);
	}

	@PutMapping("/forgot-password")
	public NotifyModel forgotPassword(@RequestBody UserModel user) {
		return UserService.forgotPassword(user.getUsername(), user.getPassword());
	}

	// delete user rest api: ok
	@DeleteMapping("/{userId}")
	public NotifyModel deleteUser(@PathVariable int userId) {
		return UserService.deleteUser(userId);
	}
}

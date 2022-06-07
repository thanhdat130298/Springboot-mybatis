package com.example.SpringMybatisXml.Services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Users.ItemUserDTO;
import com.example.SpringMybatisXml.Models.Users.RoleModel;
import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Models.Users.UsersDTO;

public interface UserService {

	UsersDTO getAllUsers(String sortBy, int pageNo, int rowSize);

	NotifyModel createUser(@Valid UserModel user, Errors errors);

	NotifyModel updateUser(int userId, UserModel userDetails, Errors err);

	NotifyModel deleteUser(int userId);

	ItemUserDTO getUserById(@Valid int userId);

	UserModel getUserByUsername(String username);

	List<RoleModel> getRoles();

	NotifyModel forgotPassword(String username, String password);

}

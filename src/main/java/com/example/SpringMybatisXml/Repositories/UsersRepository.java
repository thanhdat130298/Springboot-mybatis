package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Models.Users.UserResponse;

@Mapper
public interface UsersRepository {
	 public List<UserModel> getAllUsers();
	 public int countAll();
	 public List<UserResponse> findAll(String sortBy, int rowSize, int pageNo);
	 public UserModel getByUserId(int userId);
	 public int addUser(int userId);
	 public int updateUser(UserModel user);
	 public UserModel getLatestUser();
	 public int createUser(UserModel user);
	 public UserModel getUserByUsername(String username);
	 public UserDetails getUserByUsernameForAuthen(String username);
	 public int deleteUser(int userId);
	 public int updatePassword(String username, String password);
	 
}
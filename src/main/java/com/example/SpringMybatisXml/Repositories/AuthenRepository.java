package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringMybatisXml.Models.ModelCommon.LoginModel;
import com.example.SpringMybatisXml.Models.Users.RoleModel;

@Mapper
public interface AuthenRepository {
	 public String login(LoginModel user);
//	 public int countAll();
//	 public List<UserModel> findAll(String sortBy, int rowSize, int pageNo);
//	 public UserModel getByUserId(int userId);
//	 public AuthenRepository addUser(int userId);
//	 public UserModel getLatestUser();
//	 public int register(UserModel user);
	 public String getPassword(String username);
	 public List<RoleModel> getRoles();
	 
}
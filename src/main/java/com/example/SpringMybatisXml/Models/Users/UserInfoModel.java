package com.example.SpringMybatisXml.Models.Users;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserInfoModel extends UserModel {
	private String username;
	private String displayName;
	private int userId;
	private String roleName;
	private String password;

	public UserInfoModel(String username2, String displayName2, int userId2, String roleName2, String password2) {
		// TODO Auto-generated constructor stub
		this.username = username2;
		this.displayName = displayName2;
		this.userId = userId2;
		this.roleName = roleName2;
		this.password = password2;
	}
}

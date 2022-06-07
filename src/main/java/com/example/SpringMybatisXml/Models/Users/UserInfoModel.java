package com.example.SpringMybatisXml.Models.Users;

import lombok.Data;

@Data
public class UserInfoModel {
	private String username;
	private String displayName;
	private String roleName;
	private int userId;
	public UserInfoModel(String username, String displayName, int userId, String roleName) {
		this.username = username;
		this.displayName = displayName;
		this.userId = userId;
		this.roleName= roleName;
	}
}

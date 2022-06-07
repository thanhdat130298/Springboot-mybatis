package com.example.SpringMybatisXml.Models.Users;
// user
import java.io.Serializable;

import lombok.Data;

@Data
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private int userId;
	private String displayName;
	private int gender;
	private String birthday;
	private String phoneNumber;
	private String email;
}

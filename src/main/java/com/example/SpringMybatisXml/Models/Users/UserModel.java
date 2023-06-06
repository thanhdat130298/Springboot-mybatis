package com.example.SpringMybatisXml.Models.Users;
// user
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @NotBlank(message = " is Required")
	private String username;
	private int userId;
    @NotBlank(message = " is Required")
	private String displayName;
    @NotNull(message = " is Required")
	private int gender;
    @NotBlank(message = " is Required")
	private String birthday;
    @NotBlank(message = " is Required")
	private String phoneNumber;
    @NotBlank(message = " is Required")
	private String email;
//	private String postId; bo
    @NotBlank(message = " is Required")
	private String password;
    private boolean isActive =true;
    private String roleName;
    private int roleId;
    
}

package com.example.SpringMybatisXml.Models.ModelCommon;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
}

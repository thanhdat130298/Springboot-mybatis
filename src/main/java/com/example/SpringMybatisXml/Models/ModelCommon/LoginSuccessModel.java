package com.example.SpringMybatisXml.Models.ModelCommon;

import lombok.Data;

@Data
public class LoginSuccessModel {
	private int status = 200;
    private String message = "sucess";
    private String token = "";
    public LoginSuccessModel(String message, int status, String token) {
		// TODO Auto-generated constructor stub
    	this.status = status;
    	this.message=message;
    	this.token = token;
	}
}

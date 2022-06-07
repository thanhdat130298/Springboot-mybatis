package com.example.SpringMybatisXml.Models.ModelCommon;

import java.io.Serializable;

import com.example.SpringMybatisXml.Models.Users.UserInfoModel;

import lombok.Data;

@Data
public class TokenModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int exp; //expired
	private int iat; //time token is registered
	private UserInfoModel userInfo; //user info logged
	public TokenModel(UserInfoModel userInfo, int iat, int exp) {
		this.userInfo = userInfo;
		this.exp = exp;
		this.iat = iat;
	}
}

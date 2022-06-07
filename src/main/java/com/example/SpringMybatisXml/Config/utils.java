package com.example.SpringMybatisXml.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.SpringMybatisXml.Exception.UnAuthorized401;
import com.example.SpringMybatisXml.Models.Users.UserInfoModel;

public class utils {
	public static Integer autoCreaId(Integer id) {
		return id != null ? id + 1 : 0;
	}

	public static String validateInput(Errors errors) {
		if (errors.hasErrors()) {
			List<FieldError> err = errors.getFieldErrors();
			List<String> message = new ArrayList<>();
			for (FieldError e : err) {
				message.add(e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}
			return message.toString();
		}
		return null;
	}

	public static UserInfoModel getTokenInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object rs = null;
		if (authentication != null)
			rs = authentication.getPrincipal();
		UserInfoModel tokenInfo = (rs != null && !(rs.getClass().getName() == "java.lang.String")) ? (UserInfoModel) rs
				: null;
		if (tokenInfo != null)
			return tokenInfo;
		else
			throw new UnAuthorized401("UNAUTHORIZED");
	}

	public static Role getRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object rs = null;
		if (authentication != null)
			rs = authentication.getPrincipal();
		UserInfoModel tokenInfo = (rs != null && !(rs.getClass().getName() == "java.lang.String")) ? (UserInfoModel) rs
				: null;
		if (tokenInfo != null) {
			if (tokenInfo.getRoleName().equals("ADMIN"))
				return Role.ADMIN;
			return Role.USER;
		}
		return null;
	}
}
package com.example.SpringMybatisXml.Models.ModelCommon;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String role = "";

	@Override
	public String getAuthority() {
		return role;
	}
}
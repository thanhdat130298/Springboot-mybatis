package com.example.SpringMybatisXml.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMybatisXml.Models.ModelCommon.LoginModel;
import com.example.SpringMybatisXml.Models.ModelCommon.LoginSuccessModel;
import com.example.SpringMybatisXml.ServiceImp.AuthenImp;

@RestController
@RequestMapping("/api/v1")
public class AuthenController {

	@Autowired
	private AuthenImp AuthenService;

//
	// GET ALL
	@PostMapping("/login")
	public LoginSuccessModel login(@RequestBody LoginModel user, Errors err) {
		return AuthenService.login(user, err);
	}

}

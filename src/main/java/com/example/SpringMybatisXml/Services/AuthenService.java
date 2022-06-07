package com.example.SpringMybatisXml.Services;

import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Models.ModelCommon.LoginModel;
import com.example.SpringMybatisXml.Models.ModelCommon.LoginSuccessModel;

public interface AuthenService {

	LoginSuccessModel login(LoginModel user, Errors err) ;


}

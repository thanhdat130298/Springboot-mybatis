package com.example.SpringMybatisXml.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Config.Helper;
import com.example.SpringMybatisXml.Exception.BadRequest400;
import com.example.SpringMybatisXml.Models.ModelCommon.LoginModel;
import com.example.SpringMybatisXml.Models.ModelCommon.LoginSuccessModel;
import com.example.SpringMybatisXml.Models.Users.UserInfoModel;
import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Repositories.AuthenRepository;
import com.example.SpringMybatisXml.Services.AuthenService;
import com.example.SpringMybatisXml.Services.UserService;

@Service
public class AuthenImp implements AuthenService {

	@Autowired
	private AuthenRepository auRepo;
	@Autowired
	Helper Helper;
	@Autowired
	UserService UserService;
	
	/////////////////////////////////LOGIN
	@Override
	public LoginSuccessModel login(LoginModel user, Errors err) {
		// get username, password -> check is username is exist? -> check match password
		// by username -> return
		// username, displayName to token info =>>> get token
		LoginSuccessModel res = new LoginSuccessModel("Login fail!", 400, "");
		String passEncoded = auRepo.getPassword(user.getUsername());
		Boolean isLogin = false;
		if (passEncoded == null)
			throw new BadRequest400("User is not exist !");
		else
			isLogin = Helper.isMatchPassword(user.getPassword(), passEncoded);
		if (isLogin) {
			res.setMessage("Login success!");
			UserModel userInfo = UserService.getUserByUsername(user.getUsername());
			UserInfoModel tokenInfo = new UserInfoModel(userInfo.getUsername(), userInfo.getDisplayName(), userInfo.getUserId(), userInfo.getRoleName(), userInfo.getPassword());
			res.setToken(Helper.GenerateToken(tokenInfo));
			res.setStatus(200);
		} else {
			res.setMessage("Login fail!");

			res.setStatus(400);
		}

		return res;
	}

}

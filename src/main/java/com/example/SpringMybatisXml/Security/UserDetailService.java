package com.example.SpringMybatisXml.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringMybatisXml.Models.Users.UserInfoModel;
import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Repositories.UsersRepository;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UsersRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel user = userRepository.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		UserInfoModel userInfo = new UserInfoModel(user.getUsername(), user.getDisplayName(), user.getUserId(), user.getRoleName(),user.getPassword());
		System.out.println(userInfo.getRoleName()+"====");
		return new UserDetail(userInfo);
	}

}

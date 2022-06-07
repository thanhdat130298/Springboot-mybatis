package com.example.SpringMybatisXml.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Config.Helper;
import com.example.SpringMybatisXml.Config.Role;
import com.example.SpringMybatisXml.Config.utils;
import com.example.SpringMybatisXml.Exception.BadRequest400;
import com.example.SpringMybatisXml.Exception.NotFound404;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Users.ItemUserDTO;
import com.example.SpringMybatisXml.Models.Users.RoleModel;
import com.example.SpringMybatisXml.Models.Users.UserModel;
import com.example.SpringMybatisXml.Models.Users.UserResponse;
import com.example.SpringMybatisXml.Models.Users.UsersDTO;
import com.example.SpringMybatisXml.Repositories.AuthenRepository;
import com.example.SpringMybatisXml.Repositories.UsersRepository;
import com.example.SpringMybatisXml.Services.UserService;

@Service
public class UserImp implements UserService {

	@Autowired
	Helper Helper;

	@Autowired
	UsersRepository uRepo;
	
	@Autowired
	AuthenRepository aRepo;

//	***************** DONE
	@Override
	public UsersDTO getAllUsers(String sortBy, int pageNo, int rowSize) {
		// get context to check user or not user(admin)
//		System.out.println(uRepo.getLatestUser());
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//		Object principal = authentication.getPrincipal();
		System.out.println(utils.getRole());
		if (utils.getRole() == Role.ADMIN) {
			UsersDTO users = new UsersDTO();
			int total = uRepo.countAll();
			List<UserResponse> data = uRepo.findAll(sortBy, rowSize, pageNo > 0 ? (pageNo - 1) * rowSize : 0);
			users.setItems(data);
			users.setPageNo(pageNo > 1 ? pageNo : 1);
			users.setRowSize(rowSize);
			users.setTotal(total);
			users.setCount(data.size());
			return users;
		}
		throw new BadRequest400("You have not permission to do that!");
	}

	@Override
	public List<RoleModel> getRoles() {
		List<RoleModel> data = aRepo.getRoles();
		return data;
	}

	// **************** DONE
	@Override
	public NotifyModel createUser(UserModel user, Errors errors) {
		// get latest id user to increment
		if (errors.hasErrors()) {
			String message = utils.validateInput(errors);
			throw new BadRequest400(message);
		}
		UserModel existUser = uRepo.getUserByUsername(user.getUsername());
		if (existUser != null)
			throw new BadRequest400("User is exist!");
		String pwEncoded = Helper.EncodePassword(user.getPassword());
		user.setPassword(pwEncoded);
		UserModel lastestUser = uRepo.getLatestUser();
		user.setUserId(utils.autoCreaId(lastestUser != null ? lastestUser.getUserId() : 0));
		user.setIsActive(1);
		if (uRepo.createUser(user) != 1)
			new BadRequest400("Could not create user !");
//		ItemUserDTO u = new ItemUserDTO("success", HttpStatus.OK.value());
		return new NotifyModel("success", HttpStatus.OK.value());
	}

//	Done
	@Override
	public ItemUserDTO getUserById(int userId) {
		UserModel user = uRepo.getByUserId(userId);
		if (user == null) {
			throw new NotFound404("User is Not Exist!");
		}
		ItemUserDTO u = new ItemUserDTO("success", HttpStatus.OK.value());
		u.setUser(user);
		return u;
	}

//	Done
	@Override
	public UserModel getUserByUsername(String username) {
		UserModel user = uRepo.getUserByUsername(username);
		if (user == null) {
			throw new NotFound404("User is Not Exist!");
		}
		return user;
	}

	@Override
	public NotifyModel updateUser(int userId, UserModel userDetails, Errors errors) {
		if (errors.hasErrors()) {
			String message = utils.validateInput(errors);
			throw new BadRequest400(message);
		}
		userDetails.setUserId(userId);
		userDetails.setIsActive(1);
		if (uRepo.getByUserId(userId) == null)
			throw new NotFound404("User is Not Exist!");
		if (uRepo.updateUser(userDetails) == 0) {
			throw new BadRequest400("Could not update!");
		}
		return new NotifyModel("Updated!", HttpStatus.OK.value());
	}

	@Override
	public NotifyModel forgotPassword(String username, String password) {

		if (uRepo.getUserByUsername(username) == null)
			throw new NotFound404("User is Not Exist!");
		if (uRepo.updatePassword(username, Helper.EncodePassword(password)) == 0) {
			throw new BadRequest400("Could not change password!");
		}
		return new NotifyModel("Password had changed!", HttpStatus.OK.value());
	}

	@Override
	public NotifyModel deleteUser(int userId) {
		if (utils.getRole() == Role.ADMIN) {
			UserModel user = uRepo.getByUserId(userId);
			if (user == null)
				throw new NotFound404("User is Not Exist!");
			if (uRepo.deleteUser(userId) == 0)
				throw new BadRequest400("Could not Delete!");
			return new NotifyModel("Delete successfully!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to do that!");
	}
}

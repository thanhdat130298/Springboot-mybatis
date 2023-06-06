package com.example.SpringMybatisXml.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.SpringMybatisXml.Config.Helper;
import com.example.SpringMybatisXml.Config.Role;
import com.example.SpringMybatisXml.Config.utils;
import com.example.SpringMybatisXml.Exception.BadRequest400;
import com.example.SpringMybatisXml.Exception.NotFound404;
import com.example.SpringMybatisXml.Models.Comments.CommentModel;
import com.example.SpringMybatisXml.Models.Comments.CommentsDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Users.UserInfoModel;
import com.example.SpringMybatisXml.Repositories.CommentsRepository;
import com.example.SpringMybatisXml.Security.UserDetail;
import com.example.SpringMybatisXml.Services.CommentsService;

@Service
public class CommentImp implements CommentsService {
	@Autowired
	Helper Helper;
	@Autowired
	UserImp uService;
	@Autowired
	CommentsRepository cmtRepo;

	@Override
	public CommentsDTO getCommentsByPost(int postId, int size, int no) {
		CommentsDTO cmts = new CommentsDTO();
//		int total = 0;
//		int size = 5;
//		int no = 0;
		List<CommentModel> data = new ArrayList<>();
		data = cmtRepo.getCommentsByPost(postId, size, no > 0 ? (no - 1) * size : 0);

		cmts.setItems(data);
		return cmts;
	}

	@Override
	public NotifyModel addComment(CommentModel cmt) { // postid, content, parentid, userid
		UserDetail tokenInfo = utils.getTokenInfo();
		int uId = uService.getUserByUsername(tokenInfo.getUsername()).getUserId();
		if (uService.getUserById(uId) != null) {
			cmt.setUserId(uId);
			if (cmtRepo.addComment(cmt) != 1)
				new BadRequest400("Could not comment here!");
			return new NotifyModel("Success", HttpStatus.OK.value());
		}
		throw new NotFound404("User not found!");
	}

	@Override
	public NotifyModel updateComment(int cmtId, CommentModel cmt) {
		UserDetail tokenInfo = utils.getTokenInfo();
		int uId = uService.getUserByUsername(tokenInfo.getUsername()).getUserId();
		if (uService.getUserById(uId) != null) {
			cmt.setUserId(uId);
			cmt.setCmtId(cmtId);
			if (cmtRepo.updateComment(cmt) != 1)
				new BadRequest400("Could not update comment here!");
			return new NotifyModel("Updated!", HttpStatus.OK.value());
		}
		throw new NotFound404("User not found!");
	}

	@Override
	public CommentModel getComment(int cmtId) { // lay ra dung cmt cua dung nguoi
		UserDetail tokenInfo = utils.getTokenInfo();
		int uId = uService.getUserByUsername(tokenInfo.getUsername()).getUserId();
		if (uService.getUserById(uId) != null) {
			if (uService.getUserById(uId) != null) {
				CommentModel cmt = cmtRepo.getComment(uId, cmtId);
				if (cmt != null) {
					return cmt;
				}
				throw new NotFound404("Not found!");
			}
			throw new NotFound404("User not found!");
		}
		throw new NotFound404("User not found!");
	}

	@Override
	public NotifyModel deleteComment(int cmtId) {
		UserDetail tokenInfo = utils.getTokenInfo();
		int uId = uService.getUserByUsername(tokenInfo.getUsername()).getUserId();
		CommentModel cmt = cmtRepo.getComment(uId, cmtId);
		if (cmt != null) {
			cmtRepo.deleteComment(cmtId);
			return new NotifyModel("Deleted!", HttpStatus.OK.value());
		}
		throw new NotFound404("Not found!");
	}

}

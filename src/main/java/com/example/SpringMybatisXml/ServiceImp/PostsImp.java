package com.example.SpringMybatisXml.ServiceImp;

import java.util.ArrayList;
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
import com.example.SpringMybatisXml.Exception.UnAuthorized401;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Posts.ItemPostDTO;
import com.example.SpringMybatisXml.Models.Posts.PostModel;
import com.example.SpringMybatisXml.Models.Posts.PostsDTO;
import com.example.SpringMybatisXml.Models.Users.UserInfoModel;
import com.example.SpringMybatisXml.Repositories.CommentsRepository;
import com.example.SpringMybatisXml.Repositories.PostsRepository;
import com.example.SpringMybatisXml.Services.PostsService;

@Service
public class PostsImp implements PostsService {

	@Autowired
	Helper Helper;
	@Autowired
	UserImp uService;
	@Autowired
	PostsRepository pRepo;
	
	@Autowired
	CommentsRepository cmtRepo;

//	***************** DONE
	@Override
	public PostsDTO getAllPosts(String sortBy, int pageNo, int rowSize) {
		PostsDTO posts = new PostsDTO();
		int total = 0;
		List<PostModel> data = new ArrayList<>();
		total = pRepo.countAll();
		data = pRepo.getAllPosts(sortBy, rowSize, pageNo > 0 ? (pageNo - 1) * rowSize : 0);

		posts.setItems(data);
		posts.setPageNo(pageNo > 1 ? pageNo : 1);
		posts.setRowSize(rowSize);
		posts.setTotal(total);
		posts.setCount(data.size());

		return posts;
	}

	@Override
	public PostsDTO getMyPosts(String sortBy, int pageNo, int rowSize) {
		// get context to check user or not user(admin)
		UserInfoModel tokenInfo = utils.getTokenInfo();

		PostsDTO posts = new PostsDTO();
		int total = 0;
		List<PostModel> data = new ArrayList<>();
		if (tokenInfo != null) {
			total = pRepo.countAllByUserId(tokenInfo.getUserId());
			data = pRepo.getAllPostsByUserId(sortBy, rowSize, pageNo > 0 ? (pageNo - 1) * rowSize : 0,
					tokenInfo.getUserId());
		} else
			throw new UnAuthorized401("User not found!");

		posts.setItems(data);
		posts.setPageNo(pageNo > 1 ? pageNo : 1);
		posts.setRowSize(rowSize);
		posts.setTotal(total);
		posts.setCount(data.size());

		return posts;
	}

//
//	// **************** DONE
	@Override
	public NotifyModel createPost(PostModel post, Errors errors) {
		if (utils.getRole() == Role.ADMIN || utils.getRole() == Role.USER) {
			// get latest id user to increment
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
			UserInfoModel tokenInfo = utils.getTokenInfo();
			PostModel lastestPost = pRepo.getLatestPost();
			if (uService.getUserById(tokenInfo.getUserId()) != null)
				post.setUserId(tokenInfo.getUserId());
			post.setPostId(utils.autoCreaId(lastestPost != null ? lastestPost.getPostId() : 0));
			if (pRepo.createPost(post) != 1)
				new BadRequest400("Could not create post !");
			return new NotifyModel("Success", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to create post!");
	}

////	Done
	@Override
	public ItemPostDTO getPostById(int postId) {
		PostModel post = pRepo.getPostById(postId);
		if (post == null) {
			throw new NotFound404("Post is Not Exist!");
		}
		ItemPostDTO p = new ItemPostDTO("success", HttpStatus.OK.value());
		p.setPost(post);
		return p;
	}

	@Override
	public NotifyModel updatePost(int postId, PostModel postDetails, Errors errors) {
		if (utils.getRole() == Role.ADMIN || utils.getRole() == Role.USER) {
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
			postDetails.setPostId(postId);
			if (pRepo.getPostById(postId) == null)
				throw new NotFound404("Post is Not Exist!");
			if (pRepo.updatePost(postDetails) != 1) {
				throw new BadRequest400("Could not update!");
			}
			return new NotifyModel("Updated!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to create post!");
	}

	@Override
	public NotifyModel deletePost(int postId) {
		if (utils.getRole() == Role.ADMIN || utils.getRole() == Role.USER) {
			UserInfoModel tokenInfo = utils.getTokenInfo();
			if (pRepo.getPostById(postId) == null)
				throw new NotFound404("Post is Not Exist!");
			if (pRepo.getAllPostsByUserId("id", 10, 1, tokenInfo.getUserId()) == null)
				throw new NotFound404("You are have not any post!");

			if (pRepo.deletePost(postId) != 1)
				throw new NotFound404("Could not delete this post!");
			cmtRepo.deleteCommentByPostId(postId);
			return new NotifyModel("Deleted!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to create post!");
	}

}

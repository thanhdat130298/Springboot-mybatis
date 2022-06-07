package com.example.SpringMybatisXml.Services;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Posts.ItemPostDTO;
import com.example.SpringMybatisXml.Models.Posts.PostModel;
import com.example.SpringMybatisXml.Models.Posts.PostsDTO;

public interface PostsService {

	PostsDTO getAllPosts(String sortBy, int pageNo, int rowSize);
	PostsDTO getMyPosts(String sortBy, int pageNo, int rowSize);

	NotifyModel createPost(@Valid PostModel post, Errors errors);

	NotifyModel updatePost(int postId, PostModel postDetails, Errors err);
//
	NotifyModel deletePost(int postId);
//
	ItemPostDTO getPostById(@Valid int userId);

//	UserModel getUserByUsername(String username);


}

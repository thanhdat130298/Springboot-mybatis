package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringMybatisXml.Models.Posts.PostModel;

@Mapper
public interface PostsRepository {
	 public List<PostModel> getAllPosts();
	 public int countAll();
	 public int countAllByUserId(int userId);
	 public List<PostModel> getAllPostsByUserId(String sortBy, int rowSize, int pageNo, int userId);
	 public List<PostModel> getAllPosts(String sortBy, int rowSize, int pageNo);
	 public PostModel getPostById(int postId);
//	 public int addUser(int userId);
	 public int updatePost(PostModel post);
	 public PostModel getLatestPost();
	 public int createPost(PostModel post);
//	 public UserModel getUserByUsername(String username);
//	 public UserDetails getUserByUsernameForAuthen(String username);
	 public int deletePost(int postId);
	 
}
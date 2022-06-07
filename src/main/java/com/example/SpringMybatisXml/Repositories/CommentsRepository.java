package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringMybatisXml.Models.Comments.CommentModel;

@Mapper
public interface CommentsRepository {
	 public List<CommentModel> getCommentsByPost(int postId, int size, int no);
//	 public int countAll();
//	 public int countAllByUserId(int userId);
//	 public List<PostModel> getAllPostsByUserId(String sortBy, int rowSize, int pageNo, int userId);
//	 public List<PostModel> getAllPosts(String sortBy, int rowSize, int pageNo);
//	 public PostModel getPostById(int postId);
	 public int addComment(CommentModel cmt);
	 public int updateComment(CommentModel cmt);
	 public int deleteComment(int cmtId);
	 public int deleteCommentByPostId(int postId);
	 public CommentModel getComment(int userId, int cmtId);
//	 public int updatePost(PostModel post);
//	 public PostModel getLatestPost();
//	 public int createPost(PostModel post);
////	 public UserModel getUserByUsername(String username);
////	 public UserDetails getUserByUsernameForAuthen(String username);
//	 public int deletePost(int postId);
	 
}
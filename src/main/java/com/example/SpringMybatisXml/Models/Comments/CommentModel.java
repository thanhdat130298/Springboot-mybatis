package com.example.SpringMybatisXml.Models.Comments;

import lombok.Data;

@Data
public class CommentModel {
	private int cmtId;
	private int userId;
	private int postId;
	private int parentId;
	private String content;
	private String created;
}

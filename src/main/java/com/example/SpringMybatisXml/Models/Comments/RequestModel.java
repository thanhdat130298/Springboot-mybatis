package com.example.SpringMybatisXml.Models.Comments;

import lombok.Data;

@Data
public class RequestModel {
//	private int cmtId;
//	private int userId;
	private int postId;
	private int parentId;
	private String content;
}

package com.example.SpringMybatisXml.Models.Posts;

import lombok.Data;

@Data
public class PostModel {
		private int postId;
		private int liked;
		private String name;
		private String created;
		private int cateId;
		private int userId;
		private String content;
		private String description;
		private int actived;

}

package com.example.SpringMybatisXml.Models.Posts;
import java.util.ArrayList;
// get all list
import java.util.List;

import lombok.Data;
@Data
public class PostsDTO {
    private List<PostModel> items = new ArrayList<PostModel>();
	private int pageNo;
	private int rowSize;
	private int total;
	private int count;
}

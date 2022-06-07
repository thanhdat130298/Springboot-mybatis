package com.example.SpringMybatisXml.Models.Posts;

import lombok.Data;

@Data
public class ItemPostDTO {
    private int status = 200;
    private String message = "sucess";
    private PostModel post;
    // A default constructor is required for serialization/deserialization to work
    public ItemPostDTO(String mess, int status) {
    	message = mess;
    	this.status = status;
//    	this.post = post;
    }    
}
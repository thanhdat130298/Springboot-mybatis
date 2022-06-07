package com.example.SpringMybatisXml.Models.Comments;

import lombok.Data;

@Data
public class ItemCommentDTO {
    private int status = 200;
    private String message = "sucess";
    private CommentModel cmt;
    // A default constructor is required for serialization/deserialization to work
    public ItemCommentDTO(String mess, int status, CommentModel cmt) {
    	message = mess;
    	this.status = status;
    	this.cmt = cmt;
    }    
}
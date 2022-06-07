package com.example.SpringMybatisXml.Models.Tags;

import lombok.Data;

@Data
public class ItemTagDTO {
    private int status = 200;
    private String message = "sucess";
    private TagModel tag;
    // A default constructor is required for serialization/deserialization to work
    public ItemTagDTO(String mess, int status, TagModel tag) {
    	message = mess;
    	this.status = status;
    	this.tag = tag;
    }    
}
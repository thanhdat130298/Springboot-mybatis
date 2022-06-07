package com.example.SpringMybatisXml.Models.Categories;

import lombok.Data;

@Data
public class ItemCateDTO {
    private int status = 200;
    private String message = "sucess";
    private CategoryModel cate;
    // A default constructor is required for serialization/deserialization to work
    public ItemCateDTO(String mess, int status, CategoryModel cate) {
    	message = mess;
    	this.status = status;
    	this.cate = cate;
    }    
}
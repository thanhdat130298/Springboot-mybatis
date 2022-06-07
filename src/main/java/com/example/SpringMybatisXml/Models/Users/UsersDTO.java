package com.example.SpringMybatisXml.Models.Users;
import java.util.ArrayList;
// get all list
import java.util.List;

import lombok.Data;
@Data
public class UsersDTO {
    private List<UserResponse> items = new ArrayList<UserResponse>();
	private int pageNo;
	private int rowSize;
	private int total;
	private int count;
}

package com.example.SpringMybatisXml.Models.Users;

// status, message, user
public class ItemUserDTO {
    private int status = 200;
    private String message = "sucess";
    private UserModel user;
    // A default constructor is required for serialization/deserialization to work
    public ItemUserDTO(String mess, int status) {
    	message = mess;
    	this.status = status;
//    	this.user = user;
    }
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
package com.example.SpringMybatisXml.Models.ModelCommon;
// status, message, user
public class NotifyModel {
    private int status = 200;
    private String message = "sucess";
    // A default constructor is required for serialization/deserialization to work
    public NotifyModel(String mess, int status) {
    	message = mess;
    	this.status = status;
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
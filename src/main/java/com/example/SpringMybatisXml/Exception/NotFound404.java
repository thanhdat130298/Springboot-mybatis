package com.example.SpringMybatisXml.Exception;

public class NotFound404 extends RuntimeException {
	private static final long serialVersionUID = 1L; // chua ro~
	public NotFound404(String message) {
		super(message);
	}
}
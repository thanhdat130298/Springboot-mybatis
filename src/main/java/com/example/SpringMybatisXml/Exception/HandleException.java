package com.example.SpringMybatisXml.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Users.ItemUserDTO;

@RestControllerAdvice
public class HandleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 404 notfound
	@ExceptionHandler(value = NotFound404.class)
	public ResponseEntity<Object> NotFound404(NotFound404 exception) {
		NotifyModel res = new NotifyModel(exception.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	// 400 bad request
	@ExceptionHandler(value = BadRequest400.class)
	public ResponseEntity<Object> BadRequest(BadRequest400 exception) {
//		exception.printStackTrace();
		NotifyModel res = new NotifyModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}

	// 401 UnAuthorized
	@ExceptionHandler(value = UnAuthorized401.class)
	public ResponseEntity<Object> UnAuthorized(UnAuthorized401 exception) {
//		exception.printStackTrace();
		NotifyModel res = new NotifyModel(exception.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
	}

	// common exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnwantedException(Exception e) {
		e.printStackTrace(); // Show err in server console
		ItemUserDTO res = new ItemUserDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

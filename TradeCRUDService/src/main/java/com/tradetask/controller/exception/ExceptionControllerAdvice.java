package com.tradetask.controller.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorResponse> springHandleNotFound(HttpServletResponse response) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_IMPLEMENTED.value());
		error.setMessage("Error while inserting data");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NO_CONTENT);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setMessage("handleHttpRequestMethodNotSupportedException");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

}

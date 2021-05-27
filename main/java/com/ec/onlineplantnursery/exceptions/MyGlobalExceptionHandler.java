package com.ec.onlineplantnursery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyGlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidResourceInputByUser(ResourceNotFoundException ex) {
		
		MyExceptionResponse exeResponse = new MyExceptionResponse();
		exeResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		exeResponse.setExceptionMsg("Invalid Resources, pls try again");
		
		return new ResponseEntity<MyExceptionResponse>(exeResponse,HttpStatus.BAD_REQUEST);
	}
	
}
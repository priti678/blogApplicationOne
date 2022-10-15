package com.codewithme.blog.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithme.blog.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> ResourceNotFoundException(ResourceNotFoundException ex){
		String mesg = ex.getMessage();
		ApiResponce resp = new ApiResponce(mesg, false);
		return new ResponseEntity<ApiResponce>(resp, HttpStatus.NOT_FOUND);
	}
}

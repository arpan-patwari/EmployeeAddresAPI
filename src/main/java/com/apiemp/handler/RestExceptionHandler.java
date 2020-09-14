package com.apiemp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	
	@ExceptionHandler({Exception.class,CustomException.class})
	public ResponseEntity<Object> golabalExceptions(Exception ex){

		return new ResponseEntity<Object>(new ApiError().setErrorMessage(ex.getMessage()).build() 
											,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	

}

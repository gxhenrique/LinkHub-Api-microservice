package com.linkhub.user_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandleGlobalExceptions {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErroResponse> handleResourceNotFound(ResourceNotFound ex, HttpServletRequest request){
		
		ErroResponse err = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(), 
				"Resource not found", 
				ex.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	 @ExceptionHandler(ResourceAlreadyExists.class)
	    public ResponseEntity<ErroResponse> handleResourceAlreadyExists(
	            ResourceAlreadyExists ex,
	            HttpServletRequest request){

	        ErroResponse err = new ErroResponse(
	                LocalDateTime.now(),
	                HttpStatus.CONFLICT.value(),
	                "Resource already exists",
	                ex.getMessage(),
	                request.getRequestURI());

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(err);
	    }
}

package com.linkhub.link_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandleGlobalExceptions {
	
	@ExceptionHandler(FeignException.NotFound.class)
	public ResponseEntity<ErroResponse> handleResourceNotFound(FeignException.NotFound ex, HttpServletRequest request){
		
		ErroResponse err = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(), 
				"Resource not found",
				"Usuário informado no link não existe",
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}

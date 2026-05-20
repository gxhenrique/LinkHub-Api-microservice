package com.linkhub.user_service.exception;

public class ResourceAlreadyExists extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExists(String msg) {
		super(msg);
	}

}

package com.example.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
	private Long resourceID;

	public AuthorNotFoundException(Long resource_ID) {
		super("Author ID " + resource_ID + " not found");
		this.resourceID = resource_ID;
	}
	public Long getResourceID() {
		return resourceID;
	}

	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}

}

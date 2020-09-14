package com.apiemp.handler;

public class ApiError {
	
	private String errorMessage;


	public String getErrorMessage() {
		return errorMessage;
	}

	public ApiError setErrorMessageEntity(int entityId,String entityName) {
		this.errorMessage = entityName+" number "+entityId+" not found";
		return this;
	}
	
	public ApiError setErrorMessage(String message) {
		this.errorMessage = message;
		return this;
	}
	
	
	public ApiError build() {
		return this;
		
	}

}

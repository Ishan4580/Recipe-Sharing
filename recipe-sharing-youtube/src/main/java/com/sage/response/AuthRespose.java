package com.sage.response;

public class AuthRespose {

	
	private String jwt;
	private String message;
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AuthRespose() {
		// TODO Auto-generated constructor stub
	}
	public AuthRespose(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	}
	
	
}

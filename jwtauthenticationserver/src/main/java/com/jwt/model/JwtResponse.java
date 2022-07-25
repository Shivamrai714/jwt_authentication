package com.jwt.model;

//STEP : 9 

public class JwtResponse {

	String token;                    // token will be returned to client (POSTMAN) upon validation of the user.

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}
	
	
	
	
}

package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

	@RequestMapping("/welcome")
	public String welcome()
	{
		return "This is the home Page not accessible for normal user.";
	}
	
	@RequestMapping("/getusers")
	public String getUser()
	{
		return "{\"name\":\"Durgesh\"}" ;
	}
	
	
	@RequestMapping("/testuser")
	public String get()
	{
		return "This is tesing user" ;
	}
	
	
}

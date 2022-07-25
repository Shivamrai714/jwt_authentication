package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.service.CustomUserDetailsService;

//STEP : 7

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	//STEP 7(b):  To generate the token on first request of the client, receiving the json object from the postman , "User" with username and password.
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println("DATA  " + jwtRequest);
		
		//STEP :8 authenticate the user ,
		//also need to declare the bean for it (STEP 8 -(b))
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));			
	       
		   }
		catch (UsernameNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception(" Bad Credentials ");
			
		} catch (BadCredentialsException e) {
			
			e.printStackTrace();                    //IF EXVCEPTIONS come they can be seen in the console.
			throw new Exception(" Bad Credentials ");
		}
		
		
		
		// STEP : 9 After matching the user object with the Object stored , if no error comes, then token is generated.
	 	
		
		// if no exception catched            
		//generate the token :
		//fine area
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		//generate the Token, using the jwt class.
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT TOKEn "+ token);
		
		//covert back to json : so make a response class and generate the  Object.		
		// {"token" :"value" }
		
		//STEP : 9
	     
		return ResponseEntity.ok(new JwtResponse(token));
	
	}
	
	
	
}

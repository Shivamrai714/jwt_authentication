package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.User;
import com.jwt.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

	  //STEP 23(a)
   @Autowired
   private UserRepository userRepository;
	
	
	//STEP :2 
	//Here we are not connecting it with the database, instead using the fake/ inbuilt User method of Spring-security  to create a fake user./

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		
		
		//STEP 22 : create the REPO  and autowire its obj here
		
		//STEP 23(b):  //get user from the database
		User user = this.userRepository.findByUsername(userName);

		if(user==null)
		{
	throw new UsernameNotFoundException("User not found");
        }
		else {
			return new CustomUserDetails(user);  	    //need to return UserDetails(parent of CustomUserDe)
		}
		
		

		
		/*
		 * OLD CODE FOR THE FAKE SERVICE
		 * 
		 * 
		 * 
		  if(userName.equals("Durgesh"))
		   {  return new User("Durgesh","Durgesh123", new ArrayList<>());  
           } else
		   
		  { throw new UsernameNotFoundException("User Not found"); 
		   }

		// arraylist is passed to include roles,authorities etc of user.
		 /if User (Security default )is not impoported correctly then remove all importthe press CTRL + SHIFT + O and try multiple imports. 

	    */
	
	}

}

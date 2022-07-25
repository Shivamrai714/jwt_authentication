package com.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.model.User;

//STEP : 22
//Now here  User is our self made class so import it accordingly.

public interface UserRepository extends JpaRepository<User, Long>
{

	//customfinder method to find the user.
	
	public User findByUsername(String username);
	
}

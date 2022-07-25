/*package com.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtauthenticationserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationserverApplication.class, args);
	}

}


*/

package com.jwt;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jwt.model.User;
import com.jwt.repo.UserRepository;

//This is the base class or the starting class of the project.

@SpringBootApplication
public class JwtauthenticationserverApplication  implements CommandLineRunner{

    @Autowired	
	private UserRepository userRepository;
	
    //creating random users / otherwise can use registration form to avoid this.
    
    Random random = new Random();
    
    public void createFakeUser()
    {
    	User user = new User();
    	Long id= new Long(random.nextInt(100));
    	user.setEmail("user"+id+"@gmail.com");
    	user.setId(id);
    	user.setPassword("user"+id);
    	user.setUsername("user"+id);
    	user.setEnabled("true");
    	user.setRole("ADMIN");
    	
    	userRepository.save(user);
    }
    
    
	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationserverApplication.class, args);
	}
	
	//STEP 24 : Creating data for setting values in user table.
	//to basically create the fake user we impelements the CommandeLinerRunner which provide the thread ,override its run method. 

	@Override
	public void run(String... args) throws Exception {
		createFakeUser();
	}
	
	

	
	
	

}

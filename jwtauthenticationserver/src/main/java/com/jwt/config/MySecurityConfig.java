package com.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	 @Autowired
	 private JwtAuthenticationFilter jwtFilter;


	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
		

	 //override the methods of configure.

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(customUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
	
		http
		.csrf()                            //its is type of attack, google it cross site request forgery attack.
		.disable()
		.cors()
		.disable()
		.authorizeRequests()
		.antMatchers("/token").permitAll()              //ant matcher is used to allow url publicly and rest need to be validated
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)         
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		;
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);        //STEP 13 : to enable the handling of the Filtering method. On based of token generated. 
		
		
	}
  
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();          //use Bcrypotpassencoder when working with the databases.
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	
	
}

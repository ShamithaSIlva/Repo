package com.mifinity.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception
	{
		auth.userDetailsService( inMemoryUserDetailsManager() );
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager()
	{
		final Properties users = new Properties();
		users.put( "admin", "admin,ROLE_ADMIN,enabled" ); 
		users.put( "abc", "abc,ROLE_CUSTOMER,enabled" ); 
		return new InMemoryUserDetailsManager( users );
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage( "/login" )
		.loginProcessingUrl( "/authenticateUser" )
		.permitAll();
	}

}

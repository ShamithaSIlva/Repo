package com.mifinity.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@Component
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
		users.put( "user", "user,ROLE_CUSTOMER,enabled" ); 
		return new InMemoryUserDetailsManager( users );
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{		
		http.authorizeRequests()
		.anyRequest().authenticated()
        .antMatchers("/login")
        .permitAll()
		.and()
		.formLogin()
		.loginPage("/logincustom").loginProcessingUrl("/doLogin")
	     .usernameParameter("username").passwordParameter("password")
		.permitAll();
	}

}

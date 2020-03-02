package de.tekup.user.api.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter  {
	
	private Environment environment;

	// configure web security to accept requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// accept all requests 
		//http.authorizeRequests().antMatchers("/api/users").permitAll();
		// accept only the gateway requests  ip gateway : 192.168.43.10
		http.authorizeRequests().antMatchers("/**")
								.hasIpAddress(environment.getProperty("gateway.ip"));
	}
	
	

}

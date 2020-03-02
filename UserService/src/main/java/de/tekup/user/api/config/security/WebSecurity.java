package de.tekup.user.api.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.tekup.user.api.services.UserService;
import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter  {
	
	private Environment environment;
	private UserService usersService;
	private BCryptPasswordEncoder Bcryptencoder;

	// configure web security to accept requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// accept all requests 
		//http.authorizeRequests().antMatchers("/api/users").permitAll();
		// accept only the gateway requests  ip gateway : 192.168.43.10
		http.authorizeRequests().antMatchers("/**")
								.hasIpAddress(environment.getProperty("gateway.ip"))
								.and()
								.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(Bcryptencoder);
    }
	
	

}

package de.tekup.user.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private Environment env;
	
	@GetMapping
	public String welcome() {
		return "Welcome to User MicroService on the port :"+ env.getProperty("local.server.port");
	}

}

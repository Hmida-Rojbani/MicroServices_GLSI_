package de.tekup.user.api.endpoint;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.user.api.services.UserService;
import de.tekup.user.api.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	
	
	private Environment env;
	
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
		
		 if(userService.saveUserToDB(userDTO) != null)
			 return ResponseEntity.status(HttpStatus.CREATED)
					 			  .body("User is added to DB");
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	 			  .body("Something Wrong");
	}
	
	@GetMapping
	public String welcome() {
		return "Welcome to User MicroService on the port :"+ env.getProperty("local.server.port");
	}
	
	@GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId) {
       
        UserDTO userDto = userService.getUserByUserId(userId); 
        userDto.setPassword(null);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
	

}

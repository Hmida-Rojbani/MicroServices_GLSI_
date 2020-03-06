package de.tekup.user.api.ui.models;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@Size(min =2, message = "firstName should have more than 2 chars")
	private String name;
	@NotBlank(message = "lastName should not be empty")
	@Email
	private String email;
	
	
	private String userId;
	
	@Pattern(regexp = "$[0-9]{6-8}^")
	private String password;
	
	private List<AlbumResponseModel> albums;

}

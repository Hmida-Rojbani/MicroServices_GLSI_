package de.tekup.user.api.ui.models;

import lombok.Data;

@Data
public class LoginRequestModel {
	
	private String email;
	private String password;

}

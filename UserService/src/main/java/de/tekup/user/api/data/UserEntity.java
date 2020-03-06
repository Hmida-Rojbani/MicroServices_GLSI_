package de.tekup.user.api.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;

@Entity
@Data
public class UserEntity {
	
	@Transient
	private BCryptPasswordEncoder Bcryptencoder = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45, nullable = false)
	private String name;
	@Column(length = 45, nullable = false , unique = true)
	private String email;
	
	private String userId;
	
	private String hashedPassword;
	
	public void setPassword(String password) {
		this.hashedPassword = Bcryptencoder.encode(password);
	}

}

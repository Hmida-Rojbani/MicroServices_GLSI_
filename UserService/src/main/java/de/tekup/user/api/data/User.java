package de.tekup.user.api.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45, nullable = false)
	private String name;
	@Column(length = 45, nullable = false , unique = false)
	private String email;
	
	private String hashedPassword;

}

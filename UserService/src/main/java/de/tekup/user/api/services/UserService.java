package de.tekup.user.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.user.api.data.User;
import de.tekup.user.api.data.repos.UserRepository;
import de.tekup.user.api.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository repository;
	private ModelMapper mapper;
	
	public UserDTO saveUserToDB(UserDTO userDTO) {
		User user  = mapper.map(userDTO, User.class);
		// TODO hash the password
		user.setHashedPassword(userDTO.getPassword());
		User userSaved = repository.save(user);
		return mapper.map(userSaved, UserDTO.class);
	}
	

}

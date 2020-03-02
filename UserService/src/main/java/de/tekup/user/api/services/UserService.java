package de.tekup.user.api.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.tekup.user.api.data.UserEntity;
import de.tekup.user.api.data.repos.UserRepository;
import de.tekup.user.api.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository repository;
	private ModelMapper mapper;

	public UserDTO saveUserToDB(UserDTO userDTO) {
		UserEntity user = mapper.map(userDTO, UserEntity.class);
		// TODO hash the password
		// user.setHashedPassword(userDTO.getPassword());
		UserEntity userSaved = repository.save(user);
		return mapper.map(userSaved, UserDTO.class);
	}

	public UserDTO findUserByEmail(String email) {
		UserEntity user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));

		return mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return new User(userEntity.getEmail(), userEntity.getHashedPassword(), true, true, true, true,
				new ArrayList<>());
	}

}

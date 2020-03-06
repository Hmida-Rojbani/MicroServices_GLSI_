package de.tekup.user.api.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.tekup.user.api.data.UserEntity;
import de.tekup.user.api.data.repos.AlbumsServiceClient;
import de.tekup.user.api.data.repos.UserRepository;
import de.tekup.user.api.ui.models.AlbumResponseModel;
import de.tekup.user.api.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository repository;
	private ModelMapper mapper;
	AlbumsServiceClient albumsServiceClient;
	Environment environment;

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
	
	public UserDTO getUserByUserId(String userId) {
		UserEntity userEntity = repository.findByUserId(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));   
        
        
        UserDTO userDto = new ModelMapper().map(userEntity, UserDTO.class);
 
        
        List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
        
        
		userDto.setAlbums(albumsList);
		
		return userDto;
	}

}

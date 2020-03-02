package de.tekup.user.api.data.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.tekup.user.api.data.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);

}

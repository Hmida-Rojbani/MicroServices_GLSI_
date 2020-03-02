package de.tekup.user.api.data.repos;

import org.springframework.data.repository.CrudRepository;

import de.tekup.user.api.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

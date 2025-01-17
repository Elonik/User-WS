package com.elonik.app.ws.api.account.entity;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);

}

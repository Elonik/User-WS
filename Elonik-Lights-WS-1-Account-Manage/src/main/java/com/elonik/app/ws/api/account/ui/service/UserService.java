package com.elonik.app.ws.api.account.ui.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.elonik.app.ws.api.account.shared.UserDto;

public interface UserService extends UserDetailsService{

	UserDto CreateUser(UserDto userDtls);		
	UserDto getUserDetailsByEmail(String email);		
	
}

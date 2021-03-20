package com.elonik.app.ws.api.account.ui.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elonik.app.ws.api.account.entity.UserEntity;
import com.elonik.app.ws.api.account.entity.UsersRepository;
import com.elonik.app.ws.api.account.shared.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	UsersRepository usesrsRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UsersRepository usesrsRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usesrsRepository = usesrsRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto CreateUser(UserDto userDtls) {
		userDtls.setUserId(UUID.randomUUID().toString());
		userDtls.setEncryptedPassword(bCryptPasswordEncoder.encode(userDtls.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDtls, UserEntity.class);
		UserEntity result = usesrsRepository.save(userEntity);
		UserDto response = modelMapper.map(result, UserDto.class);
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usesrsRepository.findByEmail(username);
		
		if(userEntity==null) throw new UsernameNotFoundException(username);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = usesrsRepository.findByEmail(email);
		
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}
	
	

}

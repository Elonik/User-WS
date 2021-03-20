package com.elonik.app.ws.api.account.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elonik.app.ws.api.account.shared.UserDto;
import com.elonik.app.ws.api.account.ui.model.CreateUserRequestModel;
import com.elonik.app.ws.api.account.ui.model.CreateUserResponseModel;
import com.elonik.app.ws.api.account.ui.model.LoginRequestModel;
import com.elonik.app.ws.api.account.ui.service.UserService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userReq) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userReq, UserDto.class);
		UserDto resulut =userService.CreateUser(userDto);
		CreateUserResponseModel responseBody = modelMapper.map(resulut, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
	}
	
}

package com.elonik.app.ws.api.account.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.elonik.app.ws.api.account.shared.UserDto;
import com.elonik.app.ws.api.account.ui.model.LoginRequestModel;
import com.elonik.app.ws.api.account.ui.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private UserService userServie;
	
	@Autowired
	public AuthenticationFilter(UserService usersServie, AuthenticationManager authenticationManager ) {
		this.userServie = usersServie;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) {
		try {
			LoginRequestModel creds= new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword()));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth ) throws 
	IOException , ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDto userDto = userServie.getUserDetailsByEmail(userName);
		
		String tokens = Jwts.builder().setSubject(userDto.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + 100000L))
				.signWith(SignatureAlgorithm.HS512,"elonik123456patel" )
				.compact();
		res.addHeader("token", tokens);
		res.addHeader("userId", userDto.getUserId());
	}
}

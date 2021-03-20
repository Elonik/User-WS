package com.elonik.app.ws.api.account.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message="Firstname can not be null")
	@Size(min=2,message="Firstname must not be less than two char")
	private String firstName;
	
	@NotNull(message="Lastname can not be null")
	@Size(min=2,message="Lastname must not be less than two char")
	private String lastName;
	
	@NotNull(message="Password can not be null")
	@Size(min=8,message="Password must not be less than 8 char")
	private String password;
	
	@NotNull(message="Email can not be null")
	@Email
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

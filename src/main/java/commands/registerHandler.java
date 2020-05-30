package commands;

import services.UserServices;

public class RegisterHandler {
	
	public RegisterResponse handle(RegisterRequest r) {
		UserServices us = new UserServices();
		return new RegisterResponse(us.registerUser(r.getUsername(), r.getPassword()));
	}
	
}

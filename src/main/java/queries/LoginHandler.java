package queries;

import services.UserServices;

public class LoginHandler {
	
	public LoginResponse handle(LoginRequest r) {
		UserServices us = new UserServices();
		return new LoginResponse(us.login(r.getUsername(), r.getPassword()));
	}
}

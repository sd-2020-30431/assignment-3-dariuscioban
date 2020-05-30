package commands;

public class RegisterRequest {
	
	public String username;
	public String password;
	
	public RegisterRequest() {
		//empty no-arg constructor for kryonet
	}
	
	public RegisterRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}

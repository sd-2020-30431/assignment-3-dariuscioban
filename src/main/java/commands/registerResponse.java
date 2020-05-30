package commands;

public class RegisterResponse {
	
	public int id;
	
	public RegisterResponse() {
		//empty no-arg constructor for kryonet
	}
	
	public RegisterResponse(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

package queries;

public class LoginResponse {
	
	public int id;
	
	public LoginResponse() {
		//empty no-arg constructor for kryonet
	}
	
	public LoginResponse(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

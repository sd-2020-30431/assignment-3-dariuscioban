package commands;

public class AddItemResponse {
	
	public int id;
	
	public AddItemResponse() {
		//empty no-arg constructor for kryonet
	}
	
	public AddItemResponse(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

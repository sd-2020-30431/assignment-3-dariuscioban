package commands;

public class DeleteItemRequest {
	
	public int id;
	
	public DeleteItemRequest() {
		//empty no-arg constructor for kryonet
	}
	
	public DeleteItemRequest(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

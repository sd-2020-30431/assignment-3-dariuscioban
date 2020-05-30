package commands;

public class ConsumeItemRequest {
	
	public int id;
	
	public ConsumeItemRequest() {
		//empty no-arg constructor for kryonet
	}
	
	public ConsumeItemRequest(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

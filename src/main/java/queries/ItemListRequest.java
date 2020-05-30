package queries;

public class ItemListRequest {
	
	public int id;
	
	public ItemListRequest() {
		//empty no-arg constructor for kryonet
	}
	
	public ItemListRequest(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

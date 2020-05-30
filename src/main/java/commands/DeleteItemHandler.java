package commands;

import services.GroceryItemServices;

public class DeleteItemHandler {
	
	public void handle(DeleteItemRequest r) {
		GroceryItemServices gs = new GroceryItemServices();
		gs.deleteItem(r.getId());
	}
}

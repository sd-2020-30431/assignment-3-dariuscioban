package commands;

import services.GroceryItemServices;

public class ConsumeItemHandler {
	
	public void handle(ConsumeItemRequest r) {
		GroceryItemServices gs = new GroceryItemServices();
		gs.markItemAsConsumed(r.getId());
	}
}

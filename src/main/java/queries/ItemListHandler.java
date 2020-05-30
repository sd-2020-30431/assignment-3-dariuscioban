package queries;

import services.GroceryItemServices;

public class ItemListHandler {
	
	public ItemListResponse handle(ItemListRequest r) {
		GroceryItemServices gs = new GroceryItemServices();
		//perform automatic deletion here
		gs.automaticDeletion(r.getId());
		return new ItemListResponse(gs.getItemList(r.getId()));
	}
}

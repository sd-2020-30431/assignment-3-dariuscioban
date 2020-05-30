package commands;

import java.sql.Date;

import services.GroceryItemServices;

public class AddItemHandler {

	public AddItemResponse handle(AddItemRequest r) {
		GroceryItemServices gs = new GroceryItemServices();
		return new AddItemResponse(gs.addItem(r.getUserid(), r.getName(), r.getCalories(), r.getQuantity(),
				Date.valueOf(r.getPurchaseDate()).toLocalDate(), Date.valueOf(r.getExpirationDate()).toLocalDate()));
	}
}

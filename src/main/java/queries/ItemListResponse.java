package queries;

import java.util.ArrayList;

import communication.GroceryItemBuffer;
import model.GroceryItem;

public class ItemListResponse {
	
public ArrayList<GroceryItemBuffer> itemList;
	
	public ItemListResponse() {
		//empty no-arg constructor for kryonet
	}
	
	public ItemListResponse(ArrayList<GroceryItem> itemList) {
		this.itemList = new ArrayList<GroceryItemBuffer>();
		GroceryItemBuffer g;
		for(GroceryItem i : itemList) {
			if(i.getConsumptionDate() != null)
				g = new GroceryItemBuffer(i.getItemId(), i.getName(), i.getCalories(), i.getQuantity(),
						i.getPurchaseDate().toLocalDate().toString(), 
						i.getExpirationDate().toLocalDate().toString(), i.getConsumptionDate().toLocalDate().toString());
			else
				g = new GroceryItemBuffer(i.getItemId(), i.getName(), i.getCalories(), i.getQuantity(),
						i.getPurchaseDate().toLocalDate().toString(), i.getExpirationDate().toLocalDate().toString(), null);
			this.itemList.add(g);
		}
	}
	
	public ArrayList<GroceryItemBuffer> getItemList() {
		return itemList;
	}
}

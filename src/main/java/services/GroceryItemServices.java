package services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import data.GroceryItemQueries;
import model.GroceryItem;

public class GroceryItemServices {
	
	//-1 -- add failed
	//>0 -- add successful
	public int addItem(int userid, String name, int calories, int quantity, LocalDate purchaseDate, LocalDate expirationDate) {
		Date sqlPurchaseDate = Date.valueOf(purchaseDate);
		Date sqlExpirationDate = Date.valueOf(expirationDate);
		GroceryItem item = new GroceryItem(0, userid, name, calories, quantity, sqlPurchaseDate, sqlExpirationDate, null);
		return GroceryItemQueries.insert(item);
	}
	
	//returns the list of current existing items added by the user
	public ArrayList<GroceryItem> getItemList(int userid) {
		ArrayList<GroceryItem> itemList = GroceryItemQueries.findAllByUserId(userid);
		return itemList;
	}
	
	//it is considered that an item is marked the day it is consumed
	public void markItemAsConsumed(int itemid) {
		Date consumptionDate = Date.valueOf(LocalDate.now());
		GroceryItemQueries.update(consumptionDate, itemid);
	}
	
	//manual deletion
	public void deleteItem(int itemid) {
		GroceryItemQueries.delete(itemid);
	}
	
	//automatic deletion, when items have already expired
	public void automaticDeletion(int userid) {
		ArrayList<GroceryItem> itemList = GroceryItemQueries.findAllByUserId(userid);
		for(GroceryItem item : itemList) {
			if(item.getExpirationDate().before(Date.valueOf(LocalDate.now())))
				GroceryItemQueries.delete(item.getItemId());
		}
	}
}

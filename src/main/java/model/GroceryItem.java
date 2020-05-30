package model;

import java.sql.Date;

public class GroceryItem {
	private int itemid;
	private int userid;
	private String name;
	private int calories;
	private int quantity;
	private Date purchaseDate;
	private Date expirationDate;
	private Date consumptionDate;
	
	public GroceryItem(int itemid, int userid, String name, int calories, int quantity, Date purchaseDate, 
			Date expirationDate, Date consumptionDate) {
		this.itemid = itemid;
		this.userid = userid;
		this.name = name;
		this.calories = calories;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.expirationDate = expirationDate;
		this.consumptionDate = consumptionDate;
	}

	public int getItemId() {
		return itemid;
	}
	
	public int getUserId() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public int getCalories() {
		return calories;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	public Date getConsumptionDate() {
		return consumptionDate;
	}
}

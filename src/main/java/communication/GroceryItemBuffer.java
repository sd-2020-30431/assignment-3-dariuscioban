package communication;

import java.time.LocalDate;

public class GroceryItemBuffer {
	
	public int itemid;
	public String name;
	public int calories;
	public int quantity;
	public String purchaseDate;
	public String expirationDate;
	public String consumptionDate;
	
	public GroceryItemBuffer() {
		//empty no-arg constructor for kryonet
	}
	
	public GroceryItemBuffer(int itemid, String name, int calories, int quantity, String purchaseDate,
			String expirationDate, String consumptionDate) {
		this.itemid = itemid;
		this.name = name;
		this.calories = calories;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.expirationDate = expirationDate;
		this.consumptionDate = consumptionDate;
	}

	public int getItemid() {
		return itemid;
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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public String getConsumptionDate() {
		return consumptionDate;
	}
}

package reports;

import java.awt.Color;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import client.MainWindow;
import data.GroceryItemQueries;
import model.GroceryItem;

public class WeeklyReport implements Report{

	private int userid;
	private Color color;
	public Boolean isIdeal;
	
	public WeeklyReport(int userid) {
		this.userid = userid;
		this.color = Color.green;
		this.isIdeal = true;
	}
	
	public String printReport() {
		LocalDate dateAux = LocalDate.now();
		dateAux = dateAux.plusWeeks(1);
		Date finalDate = Date.valueOf(dateAux);
		ArrayList<GroceryItem> itemList = GroceryItemQueries.findAllByUserId(userid);
		int caloriesToExpire = 0;
		for(GroceryItem item : itemList) {
			if(item.getConsumptionDate() == null && item.getExpirationDate().before(finalDate))
				caloriesToExpire += item.getCalories();
		}
		String report = "If not consumed, " + caloriesToExpire + " calories will expire in the following week! Consider donating.";
		if((caloriesToExpire / 7) >= 20)
			isIdeal = false;
		return report;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}

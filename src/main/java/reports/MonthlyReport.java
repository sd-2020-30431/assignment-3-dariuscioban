package reports;

import java.awt.Color;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import client.MainWindow;
import data.GroceryItemQueries;
import model.GroceryItem;

public class MonthlyReport implements Report{
	
	private int userid;
	private Color color;
	public Boolean isIdeal;
	
	public MonthlyReport(int userid) {
		this.userid = userid;
	}
	
	public String printReport() {
		LocalDate dateAux = LocalDate.now();
		dateAux = dateAux.plusMonths(1);
		Date finalDate = Date.valueOf(dateAux);
		ArrayList<GroceryItem> itemList = GroceryItemQueries.findAllByUserId(userid);
		int caloriesToExpire = 0;
		for(GroceryItem item : itemList) {
			if(item.getConsumptionDate() == null && item.getExpirationDate().before(finalDate))
				caloriesToExpire += item.getCalories();
		}
		String report = "If not consumed, " + caloriesToExpire + " calories will expire in the following month! Consider donating.";
		return report;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}

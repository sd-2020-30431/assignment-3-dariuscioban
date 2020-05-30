package reports;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import client.MainWindow;
import data.GroceryItemQueries;
import model.GroceryItem;

public class WeeklyReport implements Report{

	private int userid;
	
	public WeeklyReport(int userid) {
		this.userid = userid;
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
		return report;
	}
}

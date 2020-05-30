package client;

import java.util.Observable;
import java.time.LocalDate;
import java.util.ArrayList;

import communication.GroceryItemBuffer;

public class DonationNotify extends Observable {
	
	public ArrayList<GroceryItemBuffer> list;
	public DonationNotify(MainWindow mw, ArrayList<GroceryItemBuffer> list) {
		this.list = list;
		this.addObserver(mw);
		checkWaste();
		
	}
	
	private void checkWaste() {
		int count = 0;
		String msg = "All good!";
		if(list != null) {
			for(GroceryItemBuffer g: list) {
				LocalDate expDate = LocalDate.parse(g.getExpirationDate());
				if(g.getConsumptionDate() == null && expDate.isBefore(LocalDate.now().plusDays(2))) {
					count++;
				}
			}
			msg = Integer.toString(count) + "items will expire in 2 days! Take action!";
		}
		if(list == null || count == 0)
			msg = "All good!";
		setChanged();
		notifyObservers(msg);
		
	}
	
	
}

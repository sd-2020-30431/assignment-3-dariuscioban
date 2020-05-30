package client;

import java.awt.Color;

import reports.MonthlyReport;
import reports.Report;
import reports.WeeklyReport;

public class Decorator {
	
	private Report instance;
	
	public Decorator(Report instance) {
		this.instance = instance;
	}
	
	public Color getColor() {
		if(instance instanceof MonthlyReport) {
			if(((MonthlyReport) instance).isIdeal)
				return instance.getColor();
			else return Color.red;
		} else {
			if(((WeeklyReport) instance).isIdeal)
				return instance.getColor();
			else return Color.red;
		}
		
	}
	
	public String getMsg() {
		return instance.printReport();
	}
}

package factory;

import client.MainWindow;
import reports.*;

public class MonthlyFactory extends AbstractFactory{
	
	public Report createReport(int userid) {
		return new MonthlyReport(userid);
	}
}

package factory;

import client.MainWindow;
import reports.*;

public class WeeklyFactory extends AbstractFactory{

	public Report createReport(int userid) {
		return new WeeklyReport(userid);
	}

}

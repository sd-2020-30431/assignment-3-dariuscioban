package factory;

import client.MainWindow;
import reports.Report;

public abstract class AbstractFactory {
	public abstract Report createReport(int userid);
}

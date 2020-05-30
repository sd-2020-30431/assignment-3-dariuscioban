package factory;

import reports.ReportType;

public class FactoryProvider {
	
	public static AbstractFactory getFactory(ReportType reportType) {
		if(reportType == ReportType.WEEKLY)
			return new WeeklyFactory();
		else
			return new MonthlyFactory();
	}
}

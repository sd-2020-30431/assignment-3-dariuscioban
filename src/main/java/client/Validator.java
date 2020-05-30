package client;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private static final String PASSWORD_PATTERN = "^[a-zA-Z](?=.*[0-9])(?=\\S+$).{4,}$";
	private static final String USERNAME_PATTERN = "^[a-zA-Z](?=\\S+$).{4,}$";
	private static final String NAME_PATTERN = "^[a-zA-Z].{2,}$";

	public static Boolean validateLoginInfo(String username, String password) {
        Pattern userPattern = Pattern.compile(USERNAME_PATTERN);
        Matcher userMatcher = userPattern.matcher(username);
        Pattern passPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher passMatcher = passPattern.matcher(password);
        return userMatcher.matches() && passMatcher.matches();
	}
	
	public static Boolean validateGoalInfo(int goal) {
		return goal > 0;
	}
	
	public static Boolean validateItemInfo(String name, int calories, int quantity) {
		Pattern namePattern = Pattern.compile(NAME_PATTERN);
		Matcher nameMatcher = namePattern.matcher(name);
		return nameMatcher.matches() && (calories > 0) && (quantity > 0);
	}
	
	public static Boolean dateValidator(int pYear, int pMonth, int pDay, int eYear, int eMonth, int eDay) {
		LocalDate p;
		LocalDate e;
		try {
			p = LocalDate.of(pYear, pMonth, pDay);
			e = LocalDate.of(eYear, eMonth, eDay);
		} catch (Exception exception) {
			return false;
		}
		return p.isBefore(e) && p.isBefore(LocalDate.now());
	}
}

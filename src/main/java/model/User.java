package model;

public class User {
	private int userid;
	private String username;
	private String password;
	private int goal;
	
	public User(int userid, String username, String password, int goal) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.goal = goal;
	}

	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	
}

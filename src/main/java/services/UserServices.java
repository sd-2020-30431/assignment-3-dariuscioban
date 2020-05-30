package services;

import data.UserQueries;
import model.User;

public class UserServices {
	
	//-1 -- register failed
	//> 0 -- register successful
	public int registerUser(String username, String password) {
		User user = new User(0, username, password, 0);
		int id = UserQueries.insert(user);
		return id;
	}
	
	//-1 -- login failed
	//user id -- login successful
	public int login(String username, String password) {
		User user = UserQueries.findByUsername(username);
		if(user != null && user.getPassword().equals(password))
			return user.getUserid();
		return -1;
	}
	
	public void updateGoal(int goal, int userid) {
		UserQueries.update(goal, userid);
	}
}

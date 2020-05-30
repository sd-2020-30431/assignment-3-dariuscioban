package commands;

import services.UserServices;

public class UpdateGoalHandler {
	
	public void handle(UpdateGoalRequest r) {
		UserServices us = new UserServices();
		us.updateGoal(r.getId(), r.getGoal());
	}
}

package commands;

public class UpdateGoalRequest {
	public int id;
	public int goal;
	
	public UpdateGoalRequest() {
		//empty no-arg consructor for kryonet
	}
	
	public UpdateGoalRequest(int id, int goal) {
		this.id = id;
		this.goal = goal;
	}
	
	public int getId() {
		return id;
	}
	
	public int getGoal() {
		return goal;
	}
}

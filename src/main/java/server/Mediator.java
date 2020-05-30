package server;

import commands.*;
import queries.*;

public class Mediator {
	
	public LoginHandler getHandler(LoginRequest r) {
		return new LoginHandler();
	}
	
	public RegisterHandler getHandler(RegisterRequest r) {
		return new RegisterHandler();
	}
	
	public UpdateGoalHandler getHandler(UpdateGoalRequest r) {
		return new UpdateGoalHandler();
	}
	
	public ItemListHandler getHandler(ItemListRequest r) {
		return new ItemListHandler();
	}
	
	public ConsumeItemHandler getHandler(ConsumeItemRequest r) {
		return new ConsumeItemHandler();
	}
	
	public AddItemHandler getHandler(AddItemRequest r) {
		return new AddItemHandler();
	}
	
	public DeleteItemHandler getHandler(DeleteItemRequest r) {
		return new DeleteItemHandler();
	}
}

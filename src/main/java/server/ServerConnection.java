package server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import commands.AddItemRequest;
import commands.ConsumeItemRequest;
import commands.DeleteItemRequest;
import commands.RegisterRequest;
import commands.RegisterResponse;
import commands.UpdateGoalRequest;
import communication.ClassRegister;

import queries.ItemListRequest;
import queries.ItemListResponse;
import queries.LoginRequest;
import queries.LoginResponse;

public class ServerConnection {
	
	private Server server;
	
	public ServerConnection() {
		server = new Server();
		ClassRegister.registerClasses(server);
		server.start();
		try {
			server.bind(54555, 54777);
			addListeners();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection unsuccessful.");
		}
	}
	
	private void addListeners() {
		server.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		    	  Mediator med = new Mediator();
		          if (object instanceof RegisterRequest) {
		        	  RegisterRequest request = (RegisterRequest)object;
		        	  System.out.println("UserLoginBuffer received.");
		        	  
		        	  RegisterResponse response = med.getHandler(request).handle(request);
		        	  connection.sendTCP(response);
		          }
		          
		          if (object instanceof LoginRequest) {
		        	  LoginRequest request = (LoginRequest)object;
		        	  System.out.println("UserLoginBuffer received.");
		        	  
		        	  LoginResponse response = med.getHandler(request).handle(request);
		        	  connection.sendTCP(response);
		          }
		          
		          if (object instanceof ItemListRequest) {
		        	  ItemListRequest request = (ItemListRequest)object;
		        	  System.out.println("UserIdBuffer received.");
		        	 
		        	  ItemListResponse response = med.getHandler(request).handle(request);
		        	  connection.sendTCP(response);
		          }
		          
		          if(object instanceof UpdateGoalRequest) {
		        	  UpdateGoalRequest request = (UpdateGoalRequest)object;
		        	  System.out.println("UserGoalBuffer received.");
		        	  
		        	  med.getHandler(request).handle(request);
		        	  //no response
		          }
		          
		          if(object instanceof ConsumeItemRequest) {
		        	  ConsumeItemRequest request = (ConsumeItemRequest)object;
		        	  System.out.println("GroceryItemIdBuffer received.");
		        	  
		        	  med.getHandler(request).handle(request);
		        	  //no response
		          }
		          
		          if(object instanceof DeleteItemRequest) {
		        	  DeleteItemRequest request = (DeleteItemRequest)object;
		        	  System.out.println("GroceryItemIdBuffer received.");
		        	  
		        	  med.getHandler(request).handle(request);
		        	  //no response
		          }
		          
		          if(object instanceof AddItemRequest) {
		        	  AddItemRequest request = (AddItemRequest)object;
		        	  System.out.println("GroceryIteAddBuffer received.");
		        	  
		        	  med.getHandler(request).handle(request);
		        	  //no response
		          }
		       }
		    });
	}
}

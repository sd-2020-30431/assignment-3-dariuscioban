package client;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

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

public class ClientConnection {
	
	private Client client;
	
	private LoginWindow loginWindow;
	private MainWindow mainWindow;
	
	public ClientConnection() {
		client = new Client();
		ClassRegister.registerClasses(client);
		client.start();
		try {
			client.connect(5000, "192.168.100.8", 54555, 54777);
			addListeners();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection unsuccessful.");
		}
	}
	
	private void addListeners() {
		client.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if (object instanceof LoginResponse) {
		        	  LoginResponse response = (LoginResponse)object;
		             System.out.println("UserIdBuffer received.");
		             
		             loginWindow.loginSuccess(response.getId());
		          }
		          if (object instanceof RegisterResponse) {
		        	  RegisterResponse response = (RegisterResponse)object;
		             System.out.println("UserIdBuffer received.");
		             
		             loginWindow.loginSuccess(response.getId());
		          }
		          if (object instanceof ItemListResponse) {
		        	  ItemListResponse response = (ItemListResponse)object;
		        	  System.out.println("GroceryItemListBuffer received.");
		        	  
		        	  mainWindow.updateItemList(response.getItemList());
		          }
		       }
		    });
	}
	
	public void sendLoginRequest(String username, String password) {
		LoginRequest request = new LoginRequest(username, password);
		client.sendTCP(request);
	}
	
	public void sendRegisterRequest(String username, String password) {
		RegisterRequest request = new RegisterRequest(username, password);
		client.sendTCP(request);
	}
	
	public void sendRetrieveItemList(int userId) {
		ItemListRequest request = new ItemListRequest(userId);
		client.sendTCP(request);
	}
	
	public void sendGoalRequest(int goal, int userId) {
		UpdateGoalRequest request = new UpdateGoalRequest(userId, goal);
		client.sendTCP(request);
	}
	
	public void sendConsumeRequest(int itemid) {
		ConsumeItemRequest request = new ConsumeItemRequest(itemid);
		client.sendTCP(request);
	}
	
	public void sendDeleteRequest(int itemid) {
		DeleteItemRequest request = new DeleteItemRequest(itemid);
		client.sendTCP(request);
	}
	
	public void sendAddRequest(int userId, String name, int calories, int quantity, String purchaseDate, String expirationDate) {
		AddItemRequest request = new AddItemRequest(userId, name, calories, quantity, purchaseDate, expirationDate);
		client.sendTCP(request);
	}
	
	public void addLoginWindow(LoginWindow lw) {
		loginWindow = lw;
	}
	
	public void addMainWindow(MainWindow mw) {
		mainWindow = mw;
	}
}
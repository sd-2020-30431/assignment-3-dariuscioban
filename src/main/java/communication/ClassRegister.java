package communication;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import commands.AddItemRequest;
import commands.AddItemResponse;
import commands.ConsumeItemRequest;
import commands.DeleteItemRequest;
import commands.RegisterRequest;
import commands.RegisterResponse;
import commands.UpdateGoalRequest;
import queries.ItemListRequest;
import queries.ItemListResponse;
import queries.LoginRequest;
import queries.LoginResponse;

public class ClassRegister {
	
	public static void registerClasses(EndPoint ep) {
		Kryo kryo = ep.getKryo();
		kryo.register(String.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(AddItemRequest.class);
		kryo.register(AddItemResponse.class);
		kryo.register(ConsumeItemRequest.class);
		kryo.register(DeleteItemRequest.class);
		kryo.register(GroceryItemBuffer.class);
		kryo.register(RegisterRequest.class);
		kryo.register(RegisterResponse.class);
		kryo.register(UpdateGoalRequest.class);
		kryo.register(ItemListRequest.class);
		kryo.register(ItemListResponse.class);
		kryo.register(LoginRequest.class);
		kryo.register(LoginResponse.class);
		
	}
}

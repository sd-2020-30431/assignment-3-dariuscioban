package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.GroceryItem;

public class GroceryItemQueries {
	private static final String insertStatementString = "INSERT INTO grocery_items "
			+ "(userid,name,calories,quantity,purchaseDate,expirationDate) VALUES (?,?,?,?,?,?)";
	private static final String findAllByUserIdStatementString = "SELECT * FROM grocery_items WHERE userid = ?";
	private static final String updateStatementString = "UPDATE grocery_items SET consumptionDate = ? WHERE itemid = ?";
	private static final String deleteStatementString = "DELETE FROM grocery_items WHERE itemid = ?";
	
	//inserts an item into the table
	public static int insert(GroceryItem item) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		int insertedId = -1;
		
		try {
			statement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, item.getUserId());
			statement.setString(2, item.getName());
			statement.setInt(3, item.getCalories());
			statement.setInt(4, item.getQuantity());
			statement.setDate(5, item.getPurchaseDate());
			statement.setDate(6, item.getExpirationDate());
			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				insertedId = result.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DatabaseConnection.close(statement);
			DatabaseConnection.close(connection);
		}
		return insertedId;
	}
	
	//sets the consumption date of an item
	public static void update(Date consumptionDate, int itemid) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(updateStatementString);
			statement.setDate(1, consumptionDate);
			statement.setInt(2, itemid);
			statement.executeUpdate();
		} catch (SQLException e) {

		} finally {
			DatabaseConnection.close(connection);
			DatabaseConnection.close(statement);
		}
	}
	
	//returns the list of items belonging to a certain user
	public static ArrayList<GroceryItem> findAllByUserId(int userid) {
		ArrayList<GroceryItem> ret = new ArrayList<GroceryItem>();
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(findAllByUserIdStatementString);
			statement.setInt(1, userid);
			result = statement.executeQuery();
			while(result.next()) {
				GroceryItem temp = new GroceryItem(result.getInt(1), result.getInt(2), result.getString(3),
						result.getInt(4), result.getInt(5), result.getDate(6), result.getDate(7), result.getDate(8));
				ret.add(temp);
				
			}
		} catch (SQLException e) {
			
		} finally {
			DatabaseConnection.close(result);
			DatabaseConnection.close(statement);
			DatabaseConnection.close(connection);
		}
		return ret;
	}
	
	public static void delete(int itemid) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(deleteStatementString);
			statement.setInt(1, itemid);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DatabaseConnection.close(connection);
			DatabaseConnection.close(statement);
		}
		
	}
	
}

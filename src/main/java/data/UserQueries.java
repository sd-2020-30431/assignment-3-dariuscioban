package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserQueries {
	private static final String insertStatementString = "INSERT INTO users (username,password,goal)"
			+ " VALUES (?,?,?)";
	private static final String findByUsernameStatementString = "SELECT * FROM users WHERE username = ?";
	private static final String updateStatementString = "UPDATE users SET goal = ? WHERE userid = ?";
	
	//returns the user with matching username from the database
	public static User findByUsername(String username) {
		User ret = null;
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(findByUsernameStatementString);
			statement.setString(1, username);
			result = statement.executeQuery();
			result.next();

			int id = result.getInt("userid");
			String password = result.getString("password");
			int goal = result.getInt("goal");
			ret = new User(id, username, password, goal);
		} catch (SQLException e) {

		} finally {
			DatabaseConnection.close(result);
			DatabaseConnection.close(statement);
			DatabaseConnection.close(connection);
		}
		
		return ret;
	}
	
	//register the user into the database
	public static int insert(User user) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		int insertedId = -1;
		
		try {
			statement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getGoal());
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
	
	//update the user's goal
	public static void update(int goal, int userid) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(updateStatementString);
			statement.setInt(1, goal);
			statement.setInt(2, userid);
			statement.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			DatabaseConnection.close(connection);
			DatabaseConnection.close(statement);
		}
	}
}

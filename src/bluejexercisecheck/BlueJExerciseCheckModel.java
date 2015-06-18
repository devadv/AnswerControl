package bluejexercisecheck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BlueJExerciseCheckModel {

	public static String DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
	public static String username = "ben";
	public static String password = "12345";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public BlueJExerciseCheckModel() throws SQLException {

	}

	public void setConnectionDatabase() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASEURL, username,
					password);
			statement = connection.createStatement();

		} catch (Exception connectException) {
			connectException.printStackTrace();
			System.out.println("no connection");
		}
	}

	//Create Retrieve Update and Delete

}

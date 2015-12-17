package mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Model extends Observable implements iModel  {

	public static String DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
	public static String username = "ben";
	public static String password = "12345";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Override
	public void createDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASEURL, username, password);
			statement = connection.createStatement();

		} catch (Exception connectException) {
			connectException.printStackTrace();
			System.out.println("no connection");
		}
		System.out.println("Connection made!");
	}

	@Override
	public void createQuestion(String exercise_nr, String question, int block_id) {
		String sql = "INSERT INTO correct_answer (exercise_nr, question,block_id) VALUES ('"
				+ exercise_nr + "','" + question + "','" + block_id + "')";
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		setChanged();
		notifyObservers();

	}
//questions
	@Override
	public String getQuestion(String exercise_nr) {
		String question = "";
		try {
			String sql = "SELECT question FROM correct_answer WHERE exercise_nr='" + exercise_nr
					+ "'";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			question = resultSet.getString("question");
			System.out.println(question);

		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return question;
	}

	@Override
	public void updateQuestion(String exercise_nr, String question, int block_id) {
		String sql = "UPDATE correct_answer SET question='" + question + "'WHERE exercise_nr='"+ exercise_nr +"'";
		
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		setChanged();
		notifyObservers();

	}

	@Override
	public void deleteQuestion() {
		// TODO

	}

	@Override
	public boolean isQuestion(String exercise_nr) {
		String sql = "SELECT exercise_nr FROM correct_answer WHERE exercise_nr='"
				+ exercise_nr + "'";
		try {
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				System.out.println("exsist: true");
				return true;
			} else {
				System.out.println("exsist: false");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
//answers
	@Override
	public void createAnswer(String exercise_nr, String answer, int block_id) {
		// TODO

	}

	@Override
	public String getAnswer(String exercise_nr) {
		return null;

	}

	@Override
	public void updateAnswer(String exercise_nr, String answer, int block_id) {
		// TODO

	}

	@Override
	public void deleteAnswer() {
		// TODO

	}

	@Override
	public boolean isAnswer(String exercise_nr) {
		// TODO
		return false;
	}

}
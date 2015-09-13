package bluejexercisecheck;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlueJExerciseCheckModel {

    public String DATABASEURL;
    public String username;
    public String password;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public BlueJExerciseCheckModel() throws SQLException {
       setLocation();

    }

    private void setLocation() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
             if (ip.getHostName().equals("Ubuntu1404")) {
                DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
                username = "ben";
                password = "12345";
                System.out.println("Location: Home");
            } else {
                // Never reached due to host name not connected to localhost
                // Handled in the second catch
            }
        } catch (UnknownHostException ex) {
            try {
                DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises";
                username = "badev_hintveld";
                password = "V99r9R9qwMmYPcqU";
                System.out.println("Location: Work");
                
            } catch (Exception ex2) {
                Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
            }
       }

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

    //CRUD : Create, Retrieve, Update, and Delete
    public String[] getBlockList() {

        ArrayList<String> arrayList = new ArrayList<>();

        try {

            String sql = "SELECT blockname FROM block";
            resultSet = statement.executeQuery(sql);
            int i = 0;

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);
        
        return blocks;
    }
    
    public void createQuestion(String exercise_nr, String question, int block)
			throws SQLException {

		String sql = "INSERT INTO correct_answer (exercise_nr, question,block_id) VALUES ('"
				+ exercise_nr + "','" + question + "','" + block + "')";
		statement.executeUpdate(sql);
		System.out.println(sql);

	}
    public void updateQuestion(String exercise_nr, String question) throws SQLException{
		String sql = "UPDATE correct_answer SET question='" + question + "' WHERE exercise_nr='"+ exercise_nr +"'";
				
		statement.executeUpdate(sql);
		System.out.println(sql);

	}
	
}

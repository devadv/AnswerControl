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
	public String username ;
	public String password ;

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public BlueJExerciseCheckModel() throws SQLException {
           setLocation();
                  
	}
        
        private void setLocation(){
            
            InetAddress ip;
            try {
                ip = InetAddress.getLocalHost();
                if(ip.getHostName().equals("Ubuntu1404")){
                    DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
                    username = "ben";
                    password = "12345";
                    System.out.println("Location: Home");
                }else{
                    DATABASEURL = "jdbc:mysql://10.0.0.2/badev_bluej_exercises";
                    username = "badev";
                    password = "badev";
                    System.out.println("Location: Work");
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
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

}

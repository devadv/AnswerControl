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

    /**
     * constructor with eager database connection, meaning:
     * a) username, password and location is retrieved.
     * b) and the databse connection is imediately established using these
     *    retrievals.
     * 
     * @throws SQLException 
     */
    public BlueJExerciseCheckModel() throws SQLException {
        setLocation();
        setConnectionDatabase();
    }

    private void setLocation() {
        InetAddress ip;;
        
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
                DATABASEURL = "jdbc:mysql://10.0.0.2/badev_bluej_exercises";
                username = "badev";
                password = "badev";
            }
        } catch (UnknownHostException ex) {
            try {
                DATABASEURL = "jdbc:mysql://10.0.0.2/badev_bluej_exercises";
                username = "badev";
                password = "badev";
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
    
    /**
     * A block represents a series of related questions and answers to test one 
     * or more learning subjects.
     * The block list is a list of all the blocks defined for this blueJ course.
     * ( Programmeren in Java met BlueJ vijfde editie Pearson 
     * ISBN 978-90-430-2389-4 ).
     * 
     * 
     * @return list with all block names defined for this BlueJ course.
     */
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
    
    //public String getQuestion( String exerciseNumber )
    {
        
    }
}

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

public class BlueJExerciseCheckModel 
{
   /* public String DATABASEURL;
    public String username;
    public String password;*/
	//tijdelijke oplossing voor laptop
	
    public static String DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises_test";
	public static String username = "badev_hintveld";
	public static String password = "V99r9R9qwMmYPcqU";

   //einde tijdelijke oplossing 

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * constructor with eager database connection, meaning:
     * a) username, password and location is retrieved.
     * b) and the databse connection is imediately established using these
     *    retrievals.
     *  @throws SQLException SQL exceptions are catched
     */
     
    public BlueJExerciseCheckModel() throws SQLException {
        setLocation();
        setConnectionDatabase();
    }

    /**
     * Specify properties database URL, username, password.
     */
    private void setLocation() 
    {
        InetAddress ip;;
        
        try 
        {
            ip = InetAddress.getLocalHost();
            
            if ( ip.getHostName().equals("Ubuntu1404" )) 
            {
                DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
                username = "ben";
                password = "12345";
                System.out.println( "Location: Home" );
            } 
        } 
        catch ( UnknownHostException ex ) 
        {
            try 
            {
                DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises_test";
                username = "badev_hintveld";
                password = "V99r9R9qwMmYPcqU";
                System.out.println("Location: Work");
            } 
            catch ( Exception e ) 
            {
                System.out.println( "Can not make connection to database work. " + e );
            } // end inner try catch
            
            System.out.println( "Can not make connection to database home. "  + ex );
            
        }// end outer try catch
        
    }// end setLocation

    /**
     * Make connection to a database.
     */
    
    public void setConnectionDatabase() {

        try 
        {
            Class.forName( "com.mysql.jdbc.Driver" );
            connection = DriverManager.getConnection( DATABASEURL, username, password );
            statement = connection.createStatement();
        } 
        catch ( Exception connectException ) 
        {
            connectException.printStackTrace();
            System.out.println("No connection");
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
    public String[] getBlockList() 
    {

        ArrayList<String> arrayList = new ArrayList<>();

        try 
        {

            String sql = "SELECT blockname FROM block";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                arrayList.add(resultSet.getString( 1 ) );
            }

        }
        catch ( SQLException ex ) 
        {
            System.out.println( "Error in BlueJExerciseCheckModel.BlockList." );
        }
        
        String[] blocks = arrayList.toArray( new String[arrayList.size() ] );

        return blocks;
    }
            
    /**
     * Method to adding a new question to the database.
     * @param exercise_nr the exercise number
     * @param question the question
     * @param idBlock the block number
     */    

    public void addQuestion( String exercise_nr, String question, int idBlock )
    {    
        try
        {
            String sql = "INSERT INTO correct_answer (exercise_nr, question,block_id) VALUES ('"
                + exercise_nr + "','" + question + "','" + idBlock + "')";
            statement.executeUpdate( sql );
            System.out.println( sql );
        }
        catch( SQLException ex )
        {
            
        }

    }// end method createQuestion

    /**
     * Method to updated a questioon in the database.
     * @param exercise_nr the eexercise number
     * @param question the question
     * @throws SQLException 
     * Throws a SQLException if no data is added to its parameters
     */
    
    public void updateQuestion( String exercise_nr, String question ) throws SQLException 
    {        
        String sql = "UPDATE correct_answer SET question='" + question + "' WHERE exercise_nr='" + exercise_nr + "'";

        statement.executeUpdate( sql );
        System.out.println( sql );

    }// ennd method updateQuestion

    
    public boolean exerciseExist(String exercise_nr) throws SQLException 
    {

        String sql = "SELECT exercise_nr FROM correct_answer WHERE exercise_nr='"
                + exercise_nr + "'";
        System.out.println(sql);
        resultSet = statement.executeQuery(sql);
        
        if (resultSet.next()) 
        {
            return true;
        } else 
        {
            return false;
        }
    }
    
    public String getQuestion( String exercise_nr ){//*******************************************************
        String question = "";
        try {
            String sql = "SELECT question FROM correct_answer WHERE exercise_nr='"
                    + exercise_nr + "'";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            question = resultSet.getString("question");
            System.out.println(question);
            
            
                    } catch (SQLException ex) {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
}

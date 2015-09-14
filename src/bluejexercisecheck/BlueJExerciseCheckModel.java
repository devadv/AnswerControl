package bluejexercisecheck;

import com.mysql.jdbc.exceptions.MySQLDataException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Array;
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
     *  @throws SQLException SQL exceptions are catched
     */
     
    public BlueJExerciseCheckModel() throws SQLException {
        setLocation();
        setConnectionDatabase();
    }

    private void setLocation() 
    {
        InetAddress ip;;
        
        try 
        {
            ip = InetAddress.getLocalHost();
            
            if (ip.getHostName().equals("Ubuntu1404")) {
                DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
                username = "ben";
                password = "12345";
                System.out.println("Location: Home");
            } 
            else 
            {
                // Never reached due to host name not connected to localhost
                // Handled in the second catch
                
            }
        } 
        catch (UnknownHostException ex) 
        {
            try 
            {
                DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises";
                username = "badev_hintveld";
                password = "V99r9R9qwMmYPcqU";
                System.out.println("Location: Work");
            } 
            catch (Exception e) 
            {
                Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }// end try catch
        
    }// end setLocation

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
    
    public long getBlockID( String blockText )
    {
       long row = 0;
       System.out.println("zzzz");
       
       try       
       {
           String sql = "SELECT idblock FROM block WHERE blockname = " + blockText;
           resultSet = statement.executeQuery( sql );
           resultSet.next();
           System.out.println( "xxx" + resultSet.getString( 1 ) );
           row = Long.parseLong( resultSet.getString( 1 ) );          
       }
       catch ( SQLException ex ) 
       {
           System.out.println( "Error in BlueJExerciseCheckModel.getBlockID. " + ex );
       }

       return row;
    }// end method getBlockID
    
    //
    public void addBlock( String blockName ) throws SQLException
    {
        if( blockName.isEmpty() )
        {
            throw new SQLException( "No data supplied." );
        }
        
        try 
        {
            String sql = "INSERT INTO block (blockname) VALUES ('" +  blockName + "')";
            statement.executeUpdate(sql);

        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Error in BlueJExerciseCheckModel.addBlock." );
        }
    }
    
    //
    public void addExercise( String exerciseText, long blockID ) throws SQLException
    {
        if( exerciseText.isEmpty() || blockID <= 0 )
        {
            throw new SQLException( "Exercise or block text missing. ");
        }
        
        try 
        {
            String sql = "INSERT INTO correct_answer (exercise_nr, block_id) VALUES ('" +  exerciseText + "," + blockID + "')";
            statement.executeUpdate(sql);

        }  
        catch ( SQLException ex ) 
        {
            System.out.println( "Error in BlueJExerciseCheckModel.addExercise" );
        }
    }
    
    // 
    public String[] getBlockListAllFields() 
    {

        ArrayList<String> arrayList = new ArrayList<>();

        try 
        {

            String sql = "SELECT idblock, blockname FROM block";
            resultSet = statement.executeQuery( sql );

            while (resultSet.next()) 
            {
                arrayList.add( resultSet.getString( 1 ) );
                arrayList.add( resultSet.getString( 2 ) );
            }

        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Error in BlueJExerciseCheckModel.getBlockListAllFields" );
        }
        
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);

        return blocks;
    }
     
    
    public String[] deleteBlock( long id ) throws SQLException
    {
        ArrayList<String> arrayList = new ArrayList<>();
        
        String[] blockListAll = getBlockListAllFields();
        int numberOfBlocksBeforeDelete = blockListAll.length;
        int numberOfBlocksAfterDelete = 0;
        
        try 
        {
            String sql = "DELETE from block WHERE idblock = ('" + id + "')";
            statement.executeUpdate( sql );
            
            String[] blockListAll_2 = getBlockListAllFields();
            numberOfBlocksAfterDelete = blockListAll_2.length;
                       
        } 
        catch ( SQLException ex ) 
        {
            
        }
        
        if( numberOfBlocksBeforeDelete == numberOfBlocksAfterDelete )
        {
            throw new SQLException( "None existing key." );   
        } 
        
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);

        return blocks;
    }
    
    //
    public String[] updateBlock( long id, String blockName ) throws SQLException
    {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] blockListAll = getBlockListAllFields();
        int idNumber = 0;
        boolean upDated = false;
        
        for( int i = 0; i < blockListAll.length; i += 2 )
        {
            if( id == Integer.parseInt( blockListAll[ i ] ) )
            {
                try 
                {
                    String sql = "UPDATE block SET blockname = ('" + blockName + "') WHERE idblock = (" + id + 1 + ")";
                    statement.executeUpdate(sql);
                    upDated = true;
                } 
                catch ( SQLException ex ) 
                {
                    //Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if( upDated == false )
            {
                throw new MySQLDataException( " None Existing key." );
            }
        }
                
        String[] blocks = arrayList.toArray( new String[ arrayList.size() ] );

        return blocks;
    }// end method updateBlock
    
    public void deleteAll() throws SQLException
    {
        try 
        {
            String sql = "DELETE FROM block " ;
            statement.executeUpdate( sql );
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Error in BlueJExerciseCheckModel.deletAll" );
        }
    }// end method deleteAll
    

    public void createQuestion( String exercise_nr, String question, int idBlock )
            throws SQLException 
    {
        if( exercise_nr.isEmpty() )
        {
            throw new SQLException( "exercise_nr has no data" );
        }
        else if( question.isEmpty() )
        {
            throw new SQLException( "question has no data" );
        }
        else if( idBlock < 1 || idBlock > 37 )
        {
            throw new SQLException( "idBlock must be 1 to 37 include" );
        }
        
        String sql = "INSERT INTO correct_answer (exercise_nr, question,block_id) VALUES ('"
                + exercise_nr + "','" + question + "','" + idBlock + "')";
        statement.executeUpdate( sql );
        System.out.println( sql );

    }// end method createQuestion

    public void updateQuestion( String exercise_nr, String question ) throws SQLException 
    {
        if( exercise_nr.isEmpty() )
        {
            throw new SQLException( "exercise_nr has no data");
        }
        else if( question.isEmpty() )
        {
            throw new SQLException( "question has no data" );
        }
        
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
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
    public String getQuestion(String exercise_nr ){
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

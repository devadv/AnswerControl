package bluejexercisecheck;

import com.mysql.jdbc.exceptions.MySQLDataException;
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

        try 
        {

            String sql = "SELECT blockname FROM block";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                arrayList.add(resultSet.getString(1));
            }

        } catch (SQLException ex) 
        {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);

        return blocks;
    }
    
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

        } catch (SQLException ex) 
        {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String[] getBlockListAllFields() {

        ArrayList<String> arrayList = new ArrayList<>();

        try 
        {

            String sql = "SELECT idblock, blockname FROM block";
            resultSet = statement.executeQuery( sql );

            while (resultSet.next()) 
            {
                arrayList.add(resultSet.getString(1));
                arrayList.add(resultSet.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.executeUpdate(sql);
            
            String[] blockListAll_2 = getBlockListAllFields();
            numberOfBlocksAfterDelete = blockListAll_2.length;
                       
        } catch (SQLException ex) 
        {
            //Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if( numberOfBlocksBeforeDelete == numberOfBlocksAfterDelete )
        {
            throw new SQLException("None existing key.");   
        } 
        
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);

        return blocks;
    }
    
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
                catch (SQLException ex) 
                {
                    //Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if( upDated == false )
            {
                throw new MySQLDataException( " None Existing key." );
            }
        }
                
        String[] blocks = arrayList.toArray(new String[arrayList.size()]);

        return blocks;
    }// end method updateBlock
    
    public void deleteAll() throws SQLException
    {
        try 
        {
            String sql = "DELETE FROM block " ;
            statement.executeUpdate( sql );
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BlueJExerciseCheckModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

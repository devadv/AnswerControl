package bluejexercisecheck;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Local implementation of the model
 * for testing without database.
 * @author david
 *
 */
public class BjecTestModel extends BlueJExerciseCheckModel
{
    public String DATABASEURL = "Local_test";
    public String username = "";
    public String password = "";
    
    
    private ArrayList<String> blocks = new ArrayList<String>();
    private ArrayList<String> exercise_nr = new ArrayList<String>();
    private ArrayList<String> question = new ArrayList<String>();
    		
    
    public BjecTestModel() throws SQLException
	{
        String[] blocks = {	"a", "b","c" 	};
        
        String[] exercise_nr = {"1.1","1.2","1.3","1.4"    	};
        
        String[] question = {
        		"Bla een",
        		"Bla twee",
        		"Vraag drie"
        	};
        for (String item : blocks)
        	this.blocks.add(item);

        for (String item : exercise_nr)
        	this.exercise_nr.add(item);
        
        for (String item : question)
        	this.question.add(item);
     
	
	}
    
    
    public void setConnectionDatabase()
    {
    	
    }

    
    public String[] getBlockList() 
    {
    	String[] rv = new String[0]; 
        return blocks.toArray(rv);
    }
            
    /**
     * Method to adding a new question to the database.
     * @param exercise_nr the exercise number
     * @param question the question
     * @param idBlock the block number
     */    
    public void addQuestion( String exercise_nr, String question, int idBlock )
    {    
           // Original SQL:
    	   //  "INSERT INTO correct_answer (exercise_nr, question,block_id) VALUES ('"
    	this.exercise_nr.add(exercise_nr);
    	this.question.add(question);

    }

    /**
     * Method to updated a questioon in the database.
     * @param exercise_nr the eexercise number
     * @param question the question
     * @throws SQLException 
     * Throws a SQLException if no data is added to its parameters
     */
    
    public void updateQuestion( String exercise_nr, String question ) throws SQLException 
    {        
     //   String sql = "UPDATE correct_answer SET question='" + question + "' WHERE exercise_nr='" + exercise_nr + "'";
    	int n = 0;
    	for(String item : this.exercise_nr)
    	{
    		if (item.equals(exercise_nr))
    		{
    			while (this.question.size() <= n)
    				this.question.add(null);
    			this.question.set(n, question);
    			break;
    		}
    		n++;
    		
    	}
    }

    
    public boolean exerciseExist(String exercise_nr) throws SQLException 
    {
    	for(String item : this.exercise_nr)
    	{
     		if (item.equals(exercise_nr))
    		{
     			return true;
    		}
    		
    	}
    	return false;
    }
    
    public String getQuestion( String exercise_nr )
    {
        //        String sql = "SELECT question FROM correct_answer WHERE exercise_nr='"
    	
        //   String sql = "UPDATE correct_answer SET question='" + question + "' WHERE exercise_nr='" + exercise_nr + "'";

    	Iterator<String> iter = this.exercise_nr.iterator();
    	for(int n = 0 ; iter.hasNext() && n < question.size(); n++)
    	{
    		if (iter.next().equals(exercise_nr))
    		{
    			return question.get(n);
    		}

    	}
  	
    	return "";
    }



}

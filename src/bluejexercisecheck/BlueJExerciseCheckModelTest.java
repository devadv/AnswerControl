/*
 *
 */
package bluejexercisecheck;

import java.sql.SQLException;

public class BlueJExerciseCheckModelTest 
{    
    private static BlueJExerciseCheckModel blueJExerciseCheckModel;
    
    public static void main(String[] args) 
    {
        try
        {
            blueJExerciseCheckModel = new BlueJExerciseCheckModel();  
            System.out.println("Database object gemaakt database connection is gemaakt.");
            
            // test 1: haal de huidige blocklist op.
            String[] blockList = blueJExerciseCheckModel.getBlockList();
            
            for( String block : blockList )
            {
                System.out.println( block );
            }
            
        }
        catch( SQLException e )
        {
            System.out.println( e );
        }
        
    }
}

/*
    wat ga ik doen 
    wat verwacht ik voor rsultaat
    wat is het werkelijke resultaat
*/
package bluejexercisecheck;

import java.sql.SQLException;


/*
 * CRUD test class to test persistentcy object blueJExerciseCheckModel
*/
public class BlueJExerciseCheckModelTest 
{    
    private static BlueJExerciseCheckModel blueJExerciseCheckModel;
    
    /**
     * method to show all keys of persistentcy object blueJExerciseCheckModel
     */
    public static void getBlockList()
    {
        String[] blockList = blueJExerciseCheckModel.getBlockList();
            
        for( String block : blockList )     
        {  
            System.out.println( block );
        }
            
        System.out.println();
    }
    
    /**
     * method to show all data of persistentcy object blueJExerciseCheckModel
     */
    public static void getBlockListAll()
    {
        String[] blockListAll = blueJExerciseCheckModel.getBlockListAllFields();
                
        for( int i = 0; i < blockListAll.length; i+= 2 )
        {
            System.out.println
                ( Long.parseLong(blockListAll[i]) + " " 
                + blockListAll[i+1]
                );
        }
        System.out.println();
    }
    
    /**
     * Steps to test all data access of persistentcy object 
     * blueJExerciseCheckModel 
     * 
     * @param args none
     */
    public static void main(String[] args) 
    {        
        boolean doBlockTest = false;
        boolean doDeleteAll = true;
        boolean doInsert_1 = true;
        boolean doInsert_2 = true;
        boolean doUpdate_1 = true;
        boolean doUpdate_2 = true;
        boolean doDelete_1 = true;
        boolean doDelete_2 = true;
        boolean doDeleteNoneExcisting = true;
        boolean doUpdateNoneExcisting = true;
        boolean doInsertWithOutData = true;
        
        boolean doInsert_Exercise = true;

        System.out.println("Make persistency object.");   
        
        try 
        {
            blueJExerciseCheckModel = new BlueJExerciseCheckModel();
        } 
        catch (Exception e) 
        {
            System.out.println( "Geen object of verbinding." );
        }
        
        
        if( doBlockTest )
        {               
            if( doDeleteAll )
            {
                System.out.println("Empty persistency object."); 
                
                try
                {
                    blueJExerciseCheckModel.deleteAll();
                    System.out.print( "Expected empty persistency object:\n" );
                    System.out.print( "Contents persistency object:\n" );
                    getBlockList();
                }
                catch( SQLException e )
                {
                    System.out.println( "Fout in delete all." );
                }
            } // end if doDeleteAll
            
            
            if( doInsert_1 )
            {
                System.out.println("Insert four new blocks");
                String[] addValue_1 = { "1.1", "1.2", "1.3", "1.4" };
                
                try 
                {
                    for( int i = 0; i < addValue_1.length; i++ )
                    {
                        blueJExerciseCheckModel.addBlock( addValue_1[ i ] );
                    }  
                } 
                catch (Exception e) 
                {
                    System.out.println( "Fout in doInsert_1" );
                }
                
                System.out.print( "Expected  1.1, 1.2, 1.3, 1.4\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
                
            }// end doInsert_1
            
            
            if( doInsert_2 )
            {
                System.out.println("Insert four new blocks");
                String[] addValue_2 = { "2.5", "2.6", "2.7", "2.8" };
                               
                try 
                {                    
                    for( int i = 0; i < addValue_2.length; i++ )
                    {
                        blueJExerciseCheckModel.addBlock( addValue_2[ i ] );
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println( "Fout in doInsert_2" );
                }
                
                System.out.print( "Expected 1.1, 1.2, 1.3, 1.4, 2.5, 2.6, 2.7, 2.8\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockList();
                
            }// end doInsert_2
            
            
            // 
            if( doUpdate_1 )
            {
                System.out.println( "Update one block." );
                String[] blockListAll = blueJExerciseCheckModel.getBlockListAllFields();
                int id = 0;
                
                for( int i = 0; i < blockListAll.length; i+= 2 )
                {
                    id = Integer.parseInt(blockListAll[ i ]);
                    
                    if( blockListAll[ i + 1 ].equals( "1.3" ) )
                    {
                        try 
                        {
                            blueJExerciseCheckModel.updateBlock( id, "1.3 xxx updated" );
                        } 
                        catch ( SQLException e ) 
                        {
                            
                        }
                        
                    } 
                }
                
                System.out.print( "Expected 1.3 is updated\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
                
            }// end doUpdate_1
            
            
            
            if( doUpdate_2 )
            {
                System.out.println( "Update a second block." );
                String[] blockListAll = blueJExerciseCheckModel.getBlockListAllFields();
                long id = 0;
                
                for( int i = 0; i < blockListAll.length; i+= 2 )
                {
                    id = Long.parseLong(blockListAll[i]);
                    
                    if( blockListAll[i + 1].equals("2.6") )
                    {
                        try 
                        {
                            blueJExerciseCheckModel.updateBlock( id, "2.6 xxx updated");  
                        } 
                        catch (SQLException e) 
                        {
                            
                        }
                    }  
                }
                
                System.out.print( "Expected 2.6 is updated\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }
            
            if( doDelete_1 )
            {
                System.out.println("Delete a block.");
                String[] blockListAll = blueJExerciseCheckModel.getBlockListAllFields();
                
                for( int i = 0; i < blockListAll.length; i += 2 )
                {
                    if( blockListAll[i + 1].equals( "1.1" ) )
                    {
                        try
                        {
                            int id = Integer.parseInt( blockListAll[i] );
                            blueJExerciseCheckModel.deleteBlock( id );   
                        }
                        catch( SQLException sql )
                        {
                            System.out.println( "SQLException in doDelete_1" );
                        }
                    } 
                }
                
                System.out.print( "Expected 1.1 is deleted\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }
            
            if( doDelete_2 )
            {
                System.out.println( "Delete a second block." );
                String[] blockListAll = blueJExerciseCheckModel.getBlockListAllFields();
                                
                for( int i = 0; i < blockListAll.length; i+= 2 )
                {
                   if( blockListAll[i + 1].equals( "2.8" ) )
                    {
                        try
                        {
                            int id = Integer.parseInt( blockListAll[i] );
                            blueJExerciseCheckModel.deleteBlock( id );   
                        }
                        catch( SQLException sql )
                        {
                            System.out.println( "SQLException in doDelete_2" );
                        }
                    } 
                }
                
                System.out.print( "Expected 2.8 is deleted\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }
            
            if( doDeleteNoneExcisting )
            {
                System.out.println( "Delete a none excisting block." );
                
                try
                {
                    blueJExerciseCheckModel.deleteBlock( -1 );   
                }
                catch( SQLException e )
                {
                    System.out.println( "Expecting a none existing key exception" );
                    System.out.println( "Sql exception: " + e );
                }
                                
                System.out.print( "Expected sql exception\n" );
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }// end doDeleteNoneExcisting
            
            
            if( doUpdateNoneExcisting )
            {
                System.out.println( "Update none excisting block." );
                                
                try
                {
                    blueJExerciseCheckModel.updateBlock( -1, "" );
                }
                catch( SQLException e )
                {
                    System.out.println( "Expecting a none existing key exception" );
                    System.out.println( "Sql exception: " + e );
                }
                
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }// end doUpdateNoneExcisting
            
            if( doInsertWithOutData )
            {
                System.out.println( "Insert." );
                
                try 
                {
                    blueJExerciseCheckModel.addBlock( "" );
                } 
                catch (SQLException e ) 
                {
                    System.out.println( "Expecting a no data supplied exeption: " + e );
                }
                
                System.out.print( "Contents persistency object:\n" );
                getBlockListAll();
            }// end doInsertWithOutData
            
        } // end if doTest
        
        //********************************************************************
        
        /*
         * Test exercise tabel correct_answer
         */
        
        if( doInsert_Exercise )
        {
            String[] exercises1 = { "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9" };
            String[] exercises2 = { "1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18" };
            String[] exercises3 = { "1.19", "1.20", "1.21", "1.22", "1.23", "1.24", "1.25", "1.26", "1.27" };
            String[] exercises4 = { "1.28", "1.28", "1.29", "1.30", "1.31", "1.32", "1.33", "1.34", "1.35", "1.36" };

            String[] blocks = { "1.1-1.9", "1.10-1.18", "1.19-1.27", "1.28-1.36" };

            System.out.println( "Insert exercises chapter one." );
            System.out.println("yyyy");
            
            
             
            try 
            {
                long blockID = blueJExerciseCheckModel.getBlockID( blocks[ 0 ] );
                
                for( int i = 0; i < exercises1.length; i++ )
                {
                    //blueJExerciseCheckModel.addExercise( exercises1[ i ], blockID );
                }  
            } 
            catch ( Exception e ) 
            {
                System.out.println( e );
            }

            
        }// end doInsert_Exercise
        

    }// end main
    
    
}// end class BlueJExerciseCheckModelTest


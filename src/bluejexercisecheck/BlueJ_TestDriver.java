/*
 * this a test driver to test questions and answers for the BlueJ exercises
 */
package bluejexercisecheck;

public class BlueJ_TestDriver 
{
    private static BlueJ_QandA bluej;
    private static long key;
    
    public static void main(String[] args) 
    {
        bluej = new BlueJ_QandA();
        // TODO connect and open database xxx
        // If database connection by constructor do:
        // bluej = new BlueJ_QandA("database");
        
        /*
         * Testing create, read, update, and delete (CRUD test).
         */
        
        // First test insert record
        key = bluej.insert( "xxx section number", "xxx question", "xxx answet" );
        
    }
    
    private static void readTest(long key)
    {
        /* array with output */ = bluej.read(key);
        System.out.println("xxx here the output of the read must come");
    }

}


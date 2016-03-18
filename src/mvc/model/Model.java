package mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Model extends Observable implements iModel  {

//	public static String DATABASEURL = "jdbc:mysql://localhost:3306/bluej_exercises";
//	public static String username = "ben";
//	public static String password = "12345";
	
	public static String DATABASEURL = "jdbc:mysql://sql.zz/badev_bluej_exercises_test";
	public static String username = "badev_hintveld";
	public static String password = "V99r9R9qwMmYPcqU";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Override
	public void createDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASEURL, username, password);
			statement = connection.createStatement();

		} catch (Exception connectException) {
			connectException.printStackTrace();
			System.out.println("no connection");
		}
		System.out.println("Connection made!");
	}

	@Override
	public void createQuestion(String exercise_id, String question, int block_id) {
        
//		String sql = "INSERT INTO correct_answer (exercise_nr, question, block_id) VALUES ('"
//				+ exercise_id + "','" + question + "','" + block_id + "')";
                
		try 
        {
            PreparedStatement create = connection.prepareStatement
                ( "INSERT INTO correct_answer "
                + "exercise_nr "
                + ", question "
                + ", block_id " 
                + "VALUES( ?, ?, ? )"
                );
            
                create.setString(1, exercise_id);
                create.setString(2, question);
                create.setInt(3, block_id);
                create.executeUpdate(question);
			//statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(sql);
		setChanged();
		notifyObservers();

	}
//questions
	@Override
	public String retrieveQuestion(String exercise_nr) {
        String question = "";
        
        try 
        {
            if(questionExist(exercise_nr))
            {
                try 
                {
//                    String sql = "SELECT question FROM correct_answer WHERE exercise_nr= '" + exercise_nr "'";
//                    System.out.println(sql);
//                    resultSet = statement.executeQuery(sql);
                    PreparedStatement retrieve = connection.prepareStatement
                        ( "SELECT question " 
                        + "FROM correct_answer "
                        + "WHERE exercise_nr = ? "
                        );
                    
                    retrieve.setString(1, exercise_nr);
                    retrieve.executeQuery();
                    resultSet = retrieve.getResultSet();
                    //question = resultSet.getString("question");
                    
                    while(resultSet.next())
                    {
                        question = resultSet.getString("question");
                    }
                    System.out.println("xx " + question);
                }
                catch (SQLException ex) 
                {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
                                
                return question;
            }// end if
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
	}
    
    
	@Override
	public void updateQuestion(String exercise_nr, String question, int block_id) {
		/*
        String sql = "UPDATE correct_answer SET question = '" + question + "',"
                + "creation_date = CURRENT_TIMESTAMP WHERE exercise_nr = '" + exercise_nr + "'";
        */
        
		try 
        {
             PreparedStatement update = connection.prepareStatement
                 ( "UPDATE correct_answer " 
                 + "SET creation_date = CURRENT_TIMESTAMP "
                 + ", question = ? "
               //+ ", eenKolom = waarde "    ... enz ...       
                 + "WHERE exercise_nr = ? " 
                 );
             
             update.setString(1, question);
             update.setString(2, exercise_nr);
             update.executeUpdate();
        
			//statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(sql);
		setChanged();
		notifyObservers();

	}

	@Override
	public void deactivateQuestion(String exercise_nr) 
    {
        try 
        {
            PreparedStatement deactivate = connection.prepareStatement
            ( "UPDATE correct_answer "
            + "SET deactivate_date = CURRENT_TIMESTAMP "
            + "WHERE exercise_nr = ? "
            );
            
            deactivate.setString(1, exercise_nr);
            deactivate.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
    
    @Override
    public void resetDeactivateDate()
    {
        try 
        {
            PreparedStatement reset = connection.prepareStatement
            ( "UPDATE correct_answer "
            + "SET deactivate_date = NULL "
            );
            
            reset.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

	public boolean questionExist(String exercise_nr) 
    {
//		String sql = "SELECT exercise_nr FROM correct_answer WHERE exercise_nr='"
//				+ exercise_nr + "'";
        
		try 
        {
            PreparedStatement isQuestion = connection.prepareStatement
            ( "SELECT exercise_nr "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );
            
            isQuestion.setString(1, exercise_nr);
            isQuestion.executeQuery();
            resultSet = isQuestion.getResultSet();
            
			//resultSet = statement.executeQuery(sql);
			if (resultSet.next())//resultSet.next()
            {
				System.out.println("exist: true");
				return true;
			} 
            else 
            {
				System.out.println("exist: false");
			}
		} 
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
		
	}

    
//answers
	@Override
	public void createAnswer(String exercise_nr, String answer, int block_id) {
		// TODO
		System.out.println("Create Answer in database");
	}

	@Override
	public String retrieveAnswer(String exercise_nr) {
		return "geen data beschikbaar" ;

	}

	@Override
	public void updateAnswer(String exercise_nr, String answer, int block_id) {
		System.out.println("Update Answer in database");

	}

	@Override
	public void deleteAnswer() {
		// TODO

	}

	@Override
	public boolean isAnswer(String exercise_nr) {
		// TODO
		return false;
	}

}

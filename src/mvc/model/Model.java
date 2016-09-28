package mvc.model;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Model extends Observable implements iModel  
{
    private Statement statement;
	private ResultSet resultSet;

	// properties are immutable and same for every class object. Therefore:
	private static final Properties prop = new Properties();
    private Connection connection;
    
	@Override
	public void createDBConnection() 
    {
		try 
        {
			/*
			 * load the properties
			 * 
			 * Example contents properties file:
			 * 
			 	#Tue Feb 23 14:56:57 CET 2016
			 	DB_USERNAME=John-Doe
			 	DB_DRIVER=com.mysql.jdbc.Driver
			 	DB_PASSWORD=mySuperPassword
			 	DB_URL=jdbc\:mysql\://sql.zz/badev_bluej_exercises_test
            
                DB_PASSWORD = V99r9R9qwMmYPcqU
            
            SET FOREIGN_KEY_CHECKS=0;
            TRUNCATE correct_answer;
            SET FOREIGN_KEY_CHECKS=1;
            
                SET FOREIGN_KEY_CHECKS=0;
                TRUNCATE correct_answer;
                SET FOREIGN_KEY_CHECKS=1;
			 */
			prop.load(new FileInputStream("BlueJ.config"));
			connection = 
					DriverManager.getConnection
						( prop.getProperty("DB_URL") 
						, prop.getProperty("DB_USERNAME")
						, prop.getProperty("DB_PASSWORD")
						);
			statement = connection.createStatement();
			System.out.println("Connection  database: " + prop.getProperty("DB_URL"));

		} 
        catch (Exception connectException) 
        {
			connectException.printStackTrace();
			System.out.println("no connection");
		}
		
	}
    
    
    public void saveUserName(String name)
    {        
        //INSERT INTO badev_bluej_exercises_test.`user` (username) values ('name');
        //INSERT INTO user (username) values ('name');
        if(!userNameExist(name))
        {
            try 
            {
                PreparedStatement saveUserName = connection.prepareStatement
                ( "INSERT INTO user "
                + "(username) "
                + "VALUES( ? )"
                );
                
                saveUserName.setString(1, name);
                saveUserName.execute();
            } 
            catch (Exception e) 
            {
            }
        }
    }
    
    public boolean userNameExist(String name)
    {
        try 
        {
            PreparedStatement userNameExist = connection.prepareStatement
            ( "SELECT username "
            + "FROM user "
            + "WHERE username = ? "
            );
            
            userNameExist.setString(1, name);
            userNameExist.executeQuery(); 
            resultSet = userNameExist.getResultSet();
            resultSet.next();
            
        } 
        catch (Exception e) 
        {
            return false;
        }
        return true;
    }
    
    public void createUserAnswer(String answer, String exerciseNr, String userName)
    {
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);
        int userId = getUserId(userName);  
        
            try 
            {
                PreparedStatement create = connection.prepareStatement
                    ( "INSERT INTO user_answer "
                    + "(correct_answerid "
                    + ",answer "
                    + ",userid) "
                    + "VALUES( ?, ?, ? )"
                    );

                create.setInt(1, correctAnswerId);
                create.setString(2, answer);
                create.setInt(3, userId);
                create.execute();
            } 
            catch (Exception e)
            {
            }
            
    }
        
    public void updateUserAnswer(String answer, String exerciseNr, String name)
    {
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);
        int userId = getUserId(name);
        
        try 
        {
            PreparedStatement update = connection.prepareStatement
                ( "UPDATE user_answer "
                + "SET "
                + "answer = ? "
                + "WHERE correct_answerid = ? "
                + "AND userid = ? "
                );
            
            update.setString(1, answer);
            update.setInt(2, correctAnswerId);
            update.setInt(3, userId);
            update.executeUpdate();
        } 
        catch (Exception e) 
        {
        }
    }
    
    public String getBlockName(String exerciseNr)
    {
        /*
            select badev_bluej_exercises_test.block.blockname from badev_bluej_exercises_test.correct_answer, badev_bluej_exercises_test.block
            WHERE badev_bluej_exercises_test.correct_answer.block_id = badev_bluej_exercises_test.block.idblock
            AND badev_bluej_exercises_test.correct_answer.exercise_nr = "9.1";
        */
        
        String block = "";
        
        try 
        {
            PreparedStatement blockName = connection.prepareStatement
            ( "SELECT blockname "
            + "FROM correct_answer, block "
            + "WHERE correct_answer.block_id = block.idblock "
            + "AND exercise_nr = ? "
            ); 
            
            blockName.setString(1, exerciseNr);
            blockName.executeQuery();
            resultSet = blockName.getResultSet();
            
            resultSet.next();
            block = resultSet.getString("blockname");
            
        } 
        catch (Exception e) 
        {
        }
        
        return block;
    }
    
    public String[] getExerciseList(int blockNr)
    {
        ArrayList<String> arrayList = new ArrayList<>();
        
        try 
        {
            PreparedStatement exerciseNr = connection.prepareStatement
            ( "SELECT exercise_nr "
            + "FROM correct_answer "
            + "WHERE block_id = ? "
            ); 
            
            exerciseNr.setInt(1, blockNr);
            exerciseNr.executeQuery();
            resultSet = exerciseNr.getResultSet();
            
            while (resultSet.next()) 
            {
                arrayList.add(resultSet.getString("exercise_nr"));
            }
            
        } 
        catch (Exception e)
        {
            
        }
        
        String[] exercise = arrayList.toArray(new String[arrayList.size()]);
        
        Arrays.sort(exercise);
        String[] exerciseList = new String[exercise.length];
        int j = 0;
        
        for(int i = 0; i < exercise.length; i++)
        {
            if(exercise[i].length() == 3)
            {
                exerciseList[j++] = exercise[i];
            }
        }
                
        for(int i = 0; i < exercise.length; i++)
        {
            if(exercise[i].length() == 4)
            {
                exerciseList[j++] = exercise[i];
            }
        }
        
        return exerciseList;
    }
        
    public int getIdCorrectAnswer(String exerciseNr)
    {
        int idCorrectAnswer = 0;
        
        try 
        {
            PreparedStatement retrieveID = connection.prepareStatement
            ( "SELECT idcorrect_answer "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );
            
            retrieveID.setString(1, exerciseNr);
            retrieveID.executeQuery();
            resultSet = retrieveID.getResultSet();
            
            resultSet.next();
            idCorrectAnswer = resultSet.getInt("idcorrect_answer");
        } 
        catch (Exception e) 
        {
        }
        
        return idCorrectAnswer;
    }
    
    public int getUserId(String userName)
    {
        int idUser = 0;
        
        try 
        {
            PreparedStatement getUserId = connection.prepareStatement
                ( "SELECT iduser "
                + "FROM user "
                + "WHERE username = ? "
                );

            getUserId.setString(1, userName);
            getUserId.executeQuery();
            resultSet = getUserId.getResultSet();
            
            resultSet.next();
            idUser = resultSet.getInt("iduser");
        } 
        catch (Exception e) 
        {
        }
        
        return idUser;
    }

    
	@Override
	public void createQuestion(String exercise_id, String question, int block_id) {
        
//		String sql = "INSERT INTO correct_answer (exercise_nr, question, block_id) VALUES ('"
//				+ exercise_id + "','" + question + "','" + block_id + "')";
          
		try 
        {
            PreparedStatement create = connection.prepareStatement
                ( "INSERT INTO correct_answer "
                + "(exercise_nr "
                + ", question "
                + ", block_id) " 
                + "VALUES( ?, ?, ? )"
                );
            
            create.setString(1, exercise_id);
            create.setString(2, question);
            create.setInt(3, block_id);
            create.execute();
		} 
        catch (SQLException e) 
        {
		
		}
		
		setChanged();
		notifyObservers();

	}
//questions
	@Override
	public String retrieveQuestion(String exercise_nr) {
        String question = "";
        
        if(questionExist(exercise_nr))
        {
            try 
            {
//                String sql = "SELECT question FROM correct_answer WHERE exercise_nr= '" + exercise_nr + "'";
//                System.out.println(sql);
//                resultSet = statement.executeQuery(sql);
                PreparedStatement retrieve = connection.prepareStatement
                    ( "SELECT question " 
                    + "FROM correct_answer "
                    + "WHERE exercise_nr = ? "
                    );

                retrieve.setString(1, exercise_nr);
                retrieve.executeQuery();
                resultSet = retrieve.getResultSet();

                while(resultSet.next())
                {
                    question = resultSet.getString("question");
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }

            return question;
        }
        
        return "";
	}
    
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
        }
        
        String[] blocks = arrayList.toArray( new String[arrayList.size() ] );

        return blocks;
    }
    
    public String retrieveAnswerUser(String exercise_nr, String userName)
    {
        int userId = getUserId(userName);
        int correctAnswerId = getIdCorrectAnswer(exercise_nr);
        String answerUser = "";
        
        try
        {
            PreparedStatement retrieve = connection.prepareStatement
                ( "SELECT answer "
                + "FROM user_answer "
                + "WHERE userid = ? "
                + "AND correct_answerid = ? "
                );
            
            retrieve.setInt(1, userId);
            retrieve.setInt(2, correctAnswerId);
            retrieve.execute();
            resultSet = retrieve.getResultSet();
            
            resultSet.next();
            answerUser = resultSet.getString("answer");
        }
        catch(SQLException ex)
        {       
        }
        
        return answerUser;
    }// end method retrieveAnswerUser
    
    public boolean userAnswerExist(String exerciseNr, String userName)
    {
        int userId = getUserId(userName);
        int correctAnswerId = getIdCorrectAnswer(exerciseNr);
        
        try
        {
            PreparedStatement isUserAnswer = connection.prepareStatement
            ( "SELECT answer "
            + "FROM user_answer "
            + "WHERE userid = ? "
            + "AND correct_answerid = ? "
            );
            
            isUserAnswer.setInt(1, userId);
            isUserAnswer.setInt(2, correctAnswerId);
            isUserAnswer.executeQuery();
            resultSet = isUserAnswer.getResultSet();
            
            if(resultSet.next())
            {
                return true;
            }
            
        }
        catch(SQLException ex)
        {
        }
        
        return false;
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
                 + ",question = ? "    
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
	public void updateAnswer(String exercise_nr, String answer, int block_id) 
    {
        try 
        {
             PreparedStatement update = connection.prepareStatement
                 ( "UPDATE correct_answer " 
                 + "SET creation_date = CURRENT_TIMESTAMP "
                 + ",answer = ? "    
                 + "WHERE exercise_nr = ? " 
                 );
             
             update.setString(1, answer);
             update.setString(2, exercise_nr);
             update.executeUpdate();
        
		} catch (SQLException e) 
        {
		}
        
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
            
        }
    }

	public boolean questionExist(String exercise_nr) 
    {
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
            
			if (resultSet.next())
            {
				return true;
			} 
            
		} 
        catch (SQLException e) 
        {
			
		}
        
		return false;
		
	}

    
    @Override
	public boolean answerExist(String exercise_nr) 
    {
        try
        {
            PreparedStatement answer = connection.prepareStatement
            ( "SELECT answer "
            + "FROM correct_answer "
            + "WHERE exercise_nr = ? "
            );
            
            answer.setString(1, exercise_nr);
            answer.executeQuery(); //returns the ResultSet object generated by the query.

            resultSet = answer.getResultSet();
            
            if(resultSet.next())
            {
                return true;
            }
            
        }
        catch(SQLException e)
        {
            
        }
		
		return false;
	}

	@Override
	public String retrieveAnswer(String exercise_nr) 
    {
		String answer = "";
        
        if(questionExist(exercise_nr))
        {
            try 
            {
                PreparedStatement retrieve = connection.prepareStatement
                    ( "SELECT answer " 
                    + "FROM correct_answer "
                    + "WHERE exercise_nr = ? "
                    );

                retrieve.setString(1, exercise_nr);
                retrieve.executeQuery();
                resultSet = retrieve.getResultSet();

                while(resultSet.next())
                {
                    answer = resultSet.getString("answer");
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }

            return answer;
        }
        
        return "";

	}

	
	@Override
	public void deleteAnswer() {
		// TODO

	}

    	
	public void delTable(String table)
    {
        String sql = "DELETE FROM " + table;
        
		try 
        {
            PreparedStatement del = connection.prepareStatement
                (sql);
           
            del.executeUpdate();
            resultSet = del.getResultSet();
           
        }
        catch (SQLException ex) 
        {
           
        }

		System.out.println("Table : " +table + " deleted!");
		
	}

}
